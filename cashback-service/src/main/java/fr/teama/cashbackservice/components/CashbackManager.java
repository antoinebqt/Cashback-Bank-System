package fr.teama.cashbackservice.components;

import fr.teama.cashbackservice.controllers.dto.PaymentDTO;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.ICashbackManager;
import fr.teama.cashbackservice.interfaces.proxy.IBankProxy;
import fr.teama.cashbackservice.interfaces.proxy.IMIDIterpreterProxy;
import fr.teama.cashbackservice.models.AffiliatedStore;
import fr.teama.cashbackservice.models.Cashback;
import fr.teama.cashbackservice.repository.CashbackRepository;
import fr.teama.cashbackservice.services.RabbitMQProducerService;
import fr.teama.cashbackservice.services.dto.BalanceMessage;
import fr.teama.cashbackservice.services.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CashbackManager implements ICashbackManager {

    private final IAffiliatedStoreCatalog affiliatedStoreCatalog;

    private final IBankProxy bankProxy;

    private final IMIDIterpreterProxy midInterpreterProxy;

    private final RabbitMQProducerService rabbitMQProducerService;

    private final CashbackRepository cashbackRepository;

    @Autowired
    public CashbackManager(IAffiliatedStoreCatalog affiliatedStoreCatalog, IBankProxy bankProxy, IMIDIterpreterProxy midInterpreterProxy, RabbitMQProducerService rabbitMQProducerService, CashbackRepository cashbackRepository) {
        this.affiliatedStoreCatalog = affiliatedStoreCatalog;
        this.bankProxy = bankProxy;
        this.midInterpreterProxy = midInterpreterProxy;
        this.rabbitMQProducerService = rabbitMQProducerService;
        this.cashbackRepository = cashbackRepository;
    }

    @Override
    public Double addPotentialCashback(PaymentDTO payment, Long bankAccountId) {
        AffiliatedStore affiliatedStore = affiliatedStoreCatalog.getAffiliatedStoreBySiret(payment.getSiret());
        if (affiliatedStore == null) {
            LoggerHelper.logInfo("No affiliated store founded for siret : " + payment.getSiret());
            return 0.0;
        } else {
            LoggerHelper.logInfo("Affiliated store founded for this siret");
            Double cashbackAmount = payment.getAmount() * affiliatedStore.getCashbackRate() / 100;
            LoggerHelper.logInfo("Apply cashback of " + affiliatedStore.getCashbackRate() + "% on " + payment.getAmount() + "€");
            this.bankProxy.addCashback(cashbackAmount, bankAccountId);
            return cashbackAmount;
        }
    }

    @Override
    public void processTransaction(Transaction transaction) {
        String siret = null;
        try {
            siret = this.midInterpreterProxy.getSiretByMID(transaction.getMID());
        } catch (Exception ignored) {}
        if (siret != null) {
            LoggerHelper.logInfo("Siret found for MID " + transaction.getMID() + ": " + siret);
            //TODO: send SIRET to affiliated-store-service to get cashback rate
            float cashbackRate = 10;
            if (cashbackRate == 0) {
                LoggerHelper.logInfo("No cashback rate found for this SIRET");
                return;
            }
            Double cashbackAmount = transaction.getAmount() * cashbackRate / 100;
            LoggerHelper.logInfo("Cashback rate found for this SIRET: " + cashbackRate + "%");
            LoggerHelper.logInfo("Apply cashback of " + cashbackRate + "% on " + transaction.getAmount() + "€");
            Cashback cashback = new Cashback(transaction.getBankAccountId(), cashbackAmount, transaction.getId(), siret);
            cashbackRepository.save(cashback);
            this.rabbitMQProducerService.sendBalanceMessage(new BalanceMessage(transaction.getBankAccountId(), cashbackAmount));
        }
    }
}
