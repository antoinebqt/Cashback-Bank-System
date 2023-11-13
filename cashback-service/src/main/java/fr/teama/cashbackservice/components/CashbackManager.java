package fr.teama.cashbackservice.components;

import fr.teama.cashbackservice.controllers.dto.PaymentDTO;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.IAffiliatedStoreCatalog;
import fr.teama.cashbackservice.interfaces.ICashbackManager;
import fr.teama.cashbackservice.interfaces.proxy.IBankProxy;
import fr.teama.cashbackservice.models.AffiliatedStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CashbackManager implements ICashbackManager {

    private final IAffiliatedStoreCatalog affiliatedStoreCatalog;

    private final IBankProxy bankProxy;

    @Autowired
    public CashbackManager(IAffiliatedStoreCatalog affiliatedStoreCatalog, IBankProxy bankProxy) {
        this.affiliatedStoreCatalog = affiliatedStoreCatalog;
        this.bankProxy = bankProxy;
    }

    @Override
    public Double addPotentialCashback(PaymentDTO payment, Long bankAccountId) {
        AffiliatedStore affiliatedStore = affiliatedStoreCatalog.getAffiliatedStoreByName(payment.getBeneficiary());
        if (affiliatedStore == null) {
            LoggerHelper.logInfo("No affiliated store founded for this beneficiary");
            return 0.0;
        } else {
            LoggerHelper.logInfo("Affiliated store founded for this beneficiary");
            Double cashbackAmount = payment.getAmount() * affiliatedStore.getCashbackRate() / 100;
            LoggerHelper.logInfo("Apply cashback of " + affiliatedStore.getCashbackRate() + "% on " + payment.getAmount() + "€");
            this.bankProxy.addCashback(cashbackAmount, bankAccountId);
            return cashbackAmount;
        }
    }
}
