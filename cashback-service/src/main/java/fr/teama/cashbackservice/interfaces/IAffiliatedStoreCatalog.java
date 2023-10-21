package fr.teama.cashbackservice.interfaces;

import fr.teama.cashbackservice.models.AffiliatedStore;

import java.util.List;

public interface IAffiliatedStoreCatalog {
    AffiliatedStore getAffiliatedStoreById(Long id);
    AffiliatedStore getAffiliatedStoreByName(String name);
    List<AffiliatedStore> getAllAffiliatedStores();
}
