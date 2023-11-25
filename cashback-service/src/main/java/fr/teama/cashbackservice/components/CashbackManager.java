package fr.teama.cashbackservice.components;

import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.ICashbackManager;
import fr.teama.cashbackservice.interfaces.proxy.IAffiliatedStoreProxy;
import fr.teama.cashbackservice.interfaces.proxy.IMIDIterpreterProxy;
import fr.teama.cashbackservice.models.Cashback;
import fr.teama.cashbackservice.repository.CashbackRepository;
import fr.teama.cashbackservice.services.RabbitMQProducerService;
import fr.teama.cashbackservice.services.dto.BalanceMessage;
import fr.teama.cashbackservice.services.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CashbackManager implements ICashbackManager {

    private final IMIDIterpreterProxy midInterpreterProxy;

    private final RabbitMQProducerService rabbitMQProducerService;

    private final CashbackRepository cashbackRepository;

    private final IAffiliatedStoreProxy affiliatedStoreProxy;

    @Autowired
    public CashbackManager(IMIDIterpreterProxy midInterpreterProxy, RabbitMQProducerService rabbitMQProducerService, CashbackRepository cashbackRepository, IAffiliatedStoreProxy affiliatedStoreProxy) {
        this.midInterpreterProxy = midInterpreterProxy;
        this.rabbitMQProducerService = rabbitMQProducerService;
        this.cashbackRepository = cashbackRepository;
        this.affiliatedStoreProxy = affiliatedStoreProxy;
    }

    @Override
    public void processTransaction(Transaction transaction) {
        String siret = null;
        try {
            siret = this.midInterpreterProxy.getSiretByMID(transaction.getMID());
        } catch (Exception ignored) {}
        if (siret != null) {
            LoggerHelper.logInfo("Siret found for MID " + transaction.getMID() + ": " + siret);
            float cashbackRate = affiliatedStoreProxy.getCashbackRateFromSiret(siret);
            if (cashbackRate == 0) {
                LoggerHelper.logInfo("No cashback rate found for this SIRET");
                return;
            }
            double cashbackAmount = transaction.getAmount() * cashbackRate / 100;
            LoggerHelper.logInfo("Cashback rate found for this SIRET: " + cashbackRate + "%");
            LoggerHelper.logInfo("Apply cashback of " + cashbackRate + "% on " + transaction.getAmount() + "€");
            Cashback cashback = new Cashback(transaction.getBankAccountId(), transaction.getAmount(), cashbackAmount, transaction.getId(), siret);
            cashbackRepository.save(cashback);
            this.rabbitMQProducerService.sendBalanceMessage(new BalanceMessage(transaction.getBankAccountId(), cashbackAmount));
        }
    }

    @Override
    public void cancelCashbackTransaction(String transactionId) {
        Cashback cashback = cashbackRepository.findCashbackByTransactionId(Long.valueOf(transactionId));
        if (cashback != null) {
            LoggerHelper.logInfo("Cancel cashback of transaction " + transactionId);
            cashbackRepository.delete(cashback);
            this.rabbitMQProducerService.sendBalanceMessage(new BalanceMessage(cashback.getBankAccountId(), -cashback.getAmountReturned()));
        }
    }
}
