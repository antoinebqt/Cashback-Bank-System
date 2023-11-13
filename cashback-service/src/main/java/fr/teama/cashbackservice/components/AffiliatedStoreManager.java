package fr.teama.cashbackservice.components;

import fr.teama.cashbackservice.connectors.externalDTO.TransactionDTO;
import fr.teama.cashbackservice.exceptions.AffiliatedStoreAlreadyExist;
import fr.teama.cashbackservice.interfaces.CashbackCancelAdaptor;
import fr.teama.cashbackservice.interfaces.IAffiliatedStoreCatalog;
import fr.teama.cashbackservice.interfaces.IAffiliatedStoreManager;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.proxy.IBankProxy;
import fr.teama.cashbackservice.interfaces.proxy.IStoreAPIOfType1;
import fr.teama.cashbackservice.interfaces.proxy.IStoreAPIOfType2;
import fr.teama.cashbackservice.models.AffiliatedStore;
import fr.teama.cashbackservice.models.StoreAPIType;
import fr.teama.cashbackservice.models.CashBackAnnulationParameters;
import fr.teama.cashbackservice.repository.AffiliatedStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AffiliatedStoreManager implements IAffiliatedStoreManager, IAffiliatedStoreCatalog, CashbackCancelAdaptor {

    @Autowired
    AffiliatedStoreRepository affiliatedStoreRepository;

    @Autowired
    IBankProxy bankProxy;

    @Autowired
    IStoreAPIOfType1 storeAPIOfType1;

    @Autowired
    IStoreAPIOfType2 storeAPIOfType2;


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
    public void manuallyCheckCancelledCashbackTransactions() {
        List<AffiliatedStore> affiliatedStores = affiliatedStoreRepository.findAll().stream().filter(s -> s.getCashBackAnnulationParameters().isCashBackAnnulationActivated()).toList();
        for (AffiliatedStore store : affiliatedStores) {
            List<TransactionDTO> transactionDTOList = getTransactionsToCancel(store);
            for (TransactionDTO transaction : transactionDTOList) {
                bankProxy.removeCashback(transaction);
            }
        }
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

    @Override
    public List<TransactionDTO> getTransactionsToCancel(AffiliatedStore affiliatedStore) {
        CashBackAnnulationParameters cashBackAnnulationParameters = affiliatedStore.getCashBackAnnulationParameters();
        List<String> transactionToAbort;
        System.out.println(affiliatedStore.getSiret());
        List<TransactionDTO> transactionDTOList = bankProxy.getCashbackTransactions();

        if (cashBackAnnulationParameters.getSpecificAPIConfigurationMode()== StoreAPIType.TYPE1){
            transactionToAbort=storeAPIOfType1.getCashbackTransactionsAbortedID(affiliatedStore.getCashBackAnnulationParameters().getApiForCashbackAnnulation());
        }
        else if (cashBackAnnulationParameters.getSpecificAPIConfigurationMode()== StoreAPIType.TYPE2){
            List<String> masterTransacionIdList=transactionDTOList.stream().map(transactionDTO -> transactionDTO.getPayment().getMastercardTransactionId()).collect(Collectors.toList());
            transactionToAbort=storeAPIOfType2.getCashbackTransactionsAborted(masterTransacionIdList,affiliatedStore.getCashBackAnnulationParameters().getApiForCashbackAnnulation());
        }
        else
            return new ArrayList<>();

        List<TransactionDTO> transactionsToCancel = new ArrayList<>();

        for (TransactionDTO transactionDTO : transactionDTOList) {
            if (transactionToAbort.contains(transactionDTO.getPayment().getMastercardTransactionId())){
                transactionsToCancel.add(transactionDTO);
            }
        }

        return transactionsToCancel;
    }
}
