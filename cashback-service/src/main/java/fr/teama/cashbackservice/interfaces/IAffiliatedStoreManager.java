package fr.teama.cashbackservice.interfaces;

import fr.teama.cashbackservice.exceptions.AffiliatedStoreAlreadyExist;
import fr.teama.cashbackservice.models.AffiliatedStore;

public interface IAffiliatedStoreManager {
    AffiliatedStore createAffiliatedStore(AffiliatedStore affiliatedStore) throws AffiliatedStoreAlreadyExist;

    void manuallyCheckCancelledCashbackTransactions();
}
