package interfaces;


import exceptions.AffiliatedStoreAlreadyExist;
import models.AffiliatedStore;

public interface IAffiliatedStoreManager {
    AffiliatedStore createAffiliatedStore(AffiliatedStore affiliatedStore) throws AffiliatedStoreAlreadyExist;
}
