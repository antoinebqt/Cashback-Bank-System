package fr.teama.cashbackservice.interfaces;

import fr.teama.cashbackservice.exceptions.AffiliatedStoreAlreadyExist;
import fr.teama.cashbackservice.models.AffiliatedStore;

import java.util.List;

public interface IAffiliatedStoreManager {
    AffiliatedStore createAffiliatedStore(AffiliatedStore affiliatedStore) throws AffiliatedStoreAlreadyExist;
    AffiliatedStore getAffiliatedStoreById(Long id);
    AffiliatedStore getAffiliatedStoreByName(String name);
    List<AffiliatedStore> getAllAffiliatedStores();
}
