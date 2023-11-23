package components;

import exceptions.AffiliatedStoreAlreadyExist;
import helpers.LoggerHelper;
import interfaces.IAffiliatedStoreManager;
import models.AffiliatedStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.AffiliatedStoreRepository;


@Component
public class AffiliatedStoreManager implements IAffiliatedStoreManager {

    @Autowired
    AffiliatedStoreRepository affiliatedStoreRepository;

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
