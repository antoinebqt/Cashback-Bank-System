package interfaces;


import models.AffiliatedStore;

import java.util.List;

public interface IAffiliatedStoreCatalog {
    AffiliatedStore getAffiliatedStoreById(Long id);
    AffiliatedStore getAffiliatedStoreByName(String name);
    List<AffiliatedStore> getAllAffiliatedStores();
    AffiliatedStore getAffiliatedStoreBySiret(String siret);
}
