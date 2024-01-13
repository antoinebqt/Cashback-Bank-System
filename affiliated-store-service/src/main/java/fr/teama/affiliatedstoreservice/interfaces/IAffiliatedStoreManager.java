package fr.teama.affiliatedstoreservice.interfaces;


import fr.teama.affiliatedstoreservice.exceptions.AffiliatedStoreAlreadyExist;
import fr.teama.affiliatedstoreservice.models.affiliatedstore.AffiliatedStore;

public interface IAffiliatedStoreManager {
    AffiliatedStore createAffiliatedStore(AffiliatedStore affiliatedStore) throws AffiliatedStoreAlreadyExist;
}
