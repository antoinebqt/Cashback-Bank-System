package fr.teama.affiliatedstoreservice.components;

import fr.teama.affiliatedstoreservice.exceptions.AffiliatedStoreAlreadyExist;
import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.IAffiliatedStoreManager;
import fr.teama.affiliatedstoreservice.models.affiliatedstore.AffiliatedStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fr.teama.affiliatedstoreservice.repository.affiliatedstore.AffiliatedStoreRepository;

@Component
public class AffiliatedStoreManager implements IAffiliatedStoreManager {

    AffiliatedStoreRepository affiliatedStoreRepository;

    @Autowired
    AffiliatedStoreManager(AffiliatedStoreRepository affiliatedStoreRepository){
        this.affiliatedStoreRepository = affiliatedStoreRepository;
    }

    @Override
    public AffiliatedStore createAffiliatedStore(AffiliatedStore affiliatedStore) throws AffiliatedStoreAlreadyExist {
        if (affiliatedStoreRepository.findByName(affiliatedStore.getName()) != null) {
            LoggerHelper.logError("Affiliated store already exists : " + affiliatedStore.getName());
            throw new AffiliatedStoreAlreadyExist();
        }
        LoggerHelper.logInfo("Create new affiliated store : " + affiliatedStore);
        return affiliatedStoreRepository.save(affiliatedStore);
    }
}
