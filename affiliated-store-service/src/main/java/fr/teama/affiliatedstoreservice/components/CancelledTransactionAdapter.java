package fr.teama.affiliatedstoreservice.components;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.ICancelledTransactionAdapter;
import fr.teama.affiliatedstoreservice.interfaces.proxy.IStoreAPIOfType1;
import fr.teama.affiliatedstoreservice.interfaces.proxy.IStoreAPIOfType2;
import fr.teama.affiliatedstoreservice.models.affiliatedstore.AffiliatedStore;
import fr.teama.affiliatedstoreservice.models.CashBackAnnulationParameters;
import fr.teama.affiliatedstoreservice.models.StoreAPIType;
import fr.teama.affiliatedstoreservice.models.cashback.Cashback;
import fr.teama.affiliatedstoreservice.repository.affiliatedstore.AffiliatedStoreRepository;
import fr.teama.affiliatedstoreservice.repository.cashback.CashbackRepository;
import fr.teama.affiliatedstoreservice.services.RabbitMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CancelledTransactionAdapter implements ICancelledTransactionAdapter {

    private final AffiliatedStoreRepository affiliatedStoreRepository;

    private final RabbitMQProducerService rabbitMQProducerService;

    private final CashbackRepository cashbackRepository;

    IStoreAPIOfType1 storeAPIOfType1;

    IStoreAPIOfType2 storeAPIOfType2;

    @Autowired
    public CancelledTransactionAdapter(AffiliatedStoreRepository affiliatedStoreRepository, RabbitMQProducerService rabbitMQProducerService, IStoreAPIOfType1 storeAPIOfType1, IStoreAPIOfType2 storeAPIOfType2, CashbackRepository cashbackRepository) {
        this.affiliatedStoreRepository = affiliatedStoreRepository;
        this.rabbitMQProducerService = rabbitMQProducerService;
        this.storeAPIOfType1 = storeAPIOfType1;
        this.storeAPIOfType2 = storeAPIOfType2;
        this.cashbackRepository = cashbackRepository;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void monthlyTransactionCancelledCheck() {
        LoggerHelper.logInfo("Monthly transaction cancelled check");
        checkCancelledTransaction();
    }

    @Override
    public void checkCancelledTransaction() {
        List<AffiliatedStore> affiliatedStores = affiliatedStoreRepository.findAll().stream().filter(s -> s.getCashBackAnnulationParameters().isCashBackAnnulationActivated()).toList();
        for (AffiliatedStore store : affiliatedStores) {
            List<Long> transactionIdList = getCashbacksToCancel(store);
            for (Long transactionId : transactionIdList) {
                rabbitMQProducerService.sendCashbackToCancel(transactionId);
            }
        }
    }

    private List<Long> getCashbacksToCancel(AffiliatedStore affiliatedStore) {
        CashBackAnnulationParameters cashBackAnnulationParameters = affiliatedStore.getCashBackAnnulationParameters();
        List<Long> transactionIdToAbort = new ArrayList<>();
        List<Cashback> cashbackList = cashbackRepository.findAllWithTimestampGreaterThanAndSiret(LocalDateTime.now().minusMonths(1), affiliatedStore.getSiret());
        List<String> transactionAbortedMastercardId = cashbackList.stream().map(Cashback::getMastercardTransactionId).toList(); // PROBLEME ICI, ON A BESOIN DU MASTER CARD ID JE CROIS ...

        if (cashBackAnnulationParameters.getSpecificAPIConfigurationMode() == StoreAPIType.TYPE1){
            List<String> mastercardTransactionIdToAbort = storeAPIOfType1.getCashbackTransactionsAbortedID(affiliatedStore.getCashBackAnnulationParameters().getApiForCashbackAnnulation());
            cashbackList.stream()
                    .filter(cashback -> mastercardTransactionIdToAbort.contains(cashback.getMastercardTransactionId()))
                    .forEach(cashback -> transactionIdToAbort.add(cashback.getTransactionId()));
        }
        else if (cashBackAnnulationParameters.getSpecificAPIConfigurationMode()== StoreAPIType.TYPE2){
            List<String> mastercardTransactionIdToAbort = storeAPIOfType2.getCashbackTransactionsAborted(transactionAbortedMastercardId, affiliatedStore.getCashBackAnnulationParameters().getApiForCashbackAnnulation());
            cashbackList.stream()
                    .filter(cashback -> mastercardTransactionIdToAbort.contains(cashback.getMastercardTransactionId()))
                    .forEach(cashback -> transactionIdToAbort.add(cashback.getTransactionId()));
        }

        return transactionIdToAbort;
    }
}
