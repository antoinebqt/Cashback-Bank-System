package fr.teama.cashbackservice.interfaces.proxy;

import java.util.List;

public interface IStoreAPIOfType2 {
    List<String> getCashbackTransactionsAborted(List<String> transactionAbortedID,String siret);

}
