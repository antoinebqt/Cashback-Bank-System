package fr.teama.affiliatedstoreservice.components;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.IAffiliatedStoreCatalog;
import fr.teama.affiliatedstoreservice.models.AffiliatedStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fr.teama.affiliatedstoreservice.repository.AffiliatedStoreRepository;

import java.util.List;

@Component
public class AffiliatedStoreCatalog implements IAffiliatedStoreCatalog {

    AffiliatedStoreRepository affiliatedStoreRepository;

    @Autowired
    AffiliatedStoreCatalog(AffiliatedStoreRepository affiliatedStoreRepository){
        this.affiliatedStoreRepository = affiliatedStoreRepository;
    }

    @Override
    public AffiliatedStore getAffiliatedStoreById(Long id) {
        LoggerHelper.logInfo("Get affiliated store by id : " + id);
        return affiliatedStoreRepository.findById(id).orElse(null);
    }

    @Override
    public AffiliatedStore getAffiliatedStoreBySiret(String siret) {
        LoggerHelper.logInfo("Get affiliated store by siret : " + siret);
        return affiliatedStoreRepository.findBySiret(siret);
    }

    @Override
    public AffiliatedStore getAffiliatedStoreByName(String name) {
        LoggerHelper.logInfo("Get affiliated store by name : " + name);
        return affiliatedStoreRepository.findByName(name);
    }

    @Override
    public List<AffiliatedStore> getAllAffiliatedStores() {
        LoggerHelper.logInfo("Get all affiliated stores");
        return affiliatedStoreRepository.findAll();
    }
}
