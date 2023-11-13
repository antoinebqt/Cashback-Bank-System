package fr.teama.cashbackservice.components;

import fr.teama.cashbackservice.connectors.externalDTO.TransactionDTO;
import fr.teama.cashbackservice.exceptions.AffiliatedStoreAlreadyExist;
import fr.teama.cashbackservice.interfaces.CashbackCancel;
import fr.teama.cashbackservice.interfaces.IAffiliatedStoreCatalog;
import fr.teama.cashbackservice.interfaces.IAffiliatedStoreManager;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.proxy.IBankProxy;
import fr.teama.cashbackservice.models.AffiliatedStore;
import fr.teama.cashbackservice.models.ApiConfigurationMode;
import fr.teama.cashbackservice.models.CashBackAnnulationParameters;
import fr.teama.cashbackservice.repository.AffiliatedStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AffiliatedStoreManager implements IAffiliatedStoreManager, IAffiliatedStoreCatalog, CashbackCancel {

    @Autowired
    AffiliatedStoreRepository affiliatedStoreRepository;

    @Autowired
    IBankProxy bankProxy;


    @Override
    public AffiliatedStore createAffiliatedStore(AffiliatedStore affiliatedStore) throws AffiliatedStoreAlreadyExist {
        if (affiliatedStoreRepository.findByName(affiliatedStore.getName()) != null) {
            LoggerHelper.logError("Affiliated store already exists : " + affiliatedStore.getName());
            throw new AffiliatedStoreAlreadyExist();
        }
        LoggerHelper.logInfo("Create new affiliated store : " + affiliatedStore);
        return affiliatedStoreRepository.save(affiliatedStore);
    }

    @Override
    public AffiliatedStore getAffiliatedStoreById(Long id) {
        LoggerHelper.logInfo("Get affiliated store by id : " + id);
        return affiliatedStoreRepository.findById(id).orElse(null);
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

    @Override
    public List<TransactionDTO> getTransactionsToCancel(AffiliatedStore affiliatedStore) {
        CashBackAnnulationParameters cashBackAnnulationParameters = affiliatedStore.getCashBackAnnulationParameters();
        if (cashBackAnnulationParameters.getSpecificAPIConfigurationMode()== ApiConfigurationMode.DEFAULT){

        }
        List<TransactionDTO> transactionDTOList = bankProxy.getCashbackTransactions();
        return null;
    }
}
