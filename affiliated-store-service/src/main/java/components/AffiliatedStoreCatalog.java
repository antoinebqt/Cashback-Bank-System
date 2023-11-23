package components;

import helpers.LoggerHelper;
import interfaces.IAffiliatedStoreCatalog;
import models.AffiliatedStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.AffiliatedStoreRepository;

import java.util.List;

@Component
public class AffiliatedStoreCatalog implements IAffiliatedStoreCatalog {

    @Autowired
    AffiliatedStoreRepository affiliatedStoreRepository;

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
