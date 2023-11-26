package fr.teama.affiliatedstoreservice.components;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.ICancelledTransactionAdapter;
import fr.teama.affiliatedstoreservice.interfaces.proxy.ICashbackProxy;
import fr.teama.affiliatedstoreservice.interfaces.proxy.IStoreAPIOfType1;
import fr.teama.affiliatedstoreservice.interfaces.proxy.IStoreAPIOfType2;
import fr.teama.affiliatedstoreservice.models.AffiliatedStore;
import fr.teama.affiliatedstoreservice.models.CashBackAnnulationParameters;
import fr.teama.affiliatedstoreservice.models.StoreAPIType;
import fr.teama.affiliatedstoreservice.repository.AffiliatedStoreRepository;
import fr.teama.affiliatedstoreservice.services.RabbitMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CancelledTransactionAdapter implements ICancelledTransactionAdapter {

    private final AffiliatedStoreRepository affiliatedStoreRepository;

    private final RabbitMQProducerService rabbitMQProducerService;

    private final ICashbackProxy cashbackProxy;

    IStoreAPIOfType1 storeAPIOfType1;

    IStoreAPIOfType2 storeAPIOfType2;

    @Autowired
    public CancelledTransactionAdapter(AffiliatedStoreRepository affiliatedStoreRepository, RabbitMQProducerService rabbitMQProducerService, ICashbackProxy cashbackProxy, IStoreAPIOfType1 storeAPIOfType1, IStoreAPIOfType2 storeAPIOfType2) {
        this.affiliatedStoreRepository = affiliatedStoreRepository;
        this.rabbitMQProducerService = rabbitMQProducerService;
        this.cashbackProxy = cashbackProxy;
        this.storeAPIOfType1 = storeAPIOfType1;
        this.storeAPIOfType2 = storeAPIOfType2;
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
            List<String> transactionIdList = getCashbacksToCancel(store);
            for (String transactionId : transactionIdList) {
                rabbitMQProducerService.sendCashbackToCancel(transactionId);
            }
        }
    }

    private List<String> getCashbacksToCancel(AffiliatedStore affiliatedStore) {
        CashBackAnnulationParameters cashBackAnnulationParameters = affiliatedStore.getCashBackAnnulationParameters();
        List<String> transactionIdToAbort = new ArrayList<>();
        List<String> cashbackDTOListDTOList = cashbackProxy.getCashbackTransactionIdsLastMonthWithSiret(affiliatedStore.getSiret());

        if (cashBackAnnulationParameters.getSpecificAPIConfigurationMode() == StoreAPIType.TYPE1){
            transactionIdToAbort = storeAPIOfType1.getCashbackTransactionsAbortedID(affiliatedStore.getCashBackAnnulationParameters().getApiForCashbackAnnulation());
        }
        else if (cashBackAnnulationParameters.getSpecificAPIConfigurationMode()== StoreAPIType.TYPE2){
            transactionIdToAbort = storeAPIOfType2.getCashbackTransactionsAborted(cashbackDTOListDTOList, affiliatedStore.getCashBackAnnulationParameters().getApiForCashbackAnnulation());
        }

        return transactionIdToAbort;
    }
}
