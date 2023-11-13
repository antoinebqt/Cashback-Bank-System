package fr.teama.cashbackservice.interfaces.proxy;

import java.util.List;

public interface IStoreAPIOfType2 {
    List<Long> getCashbackTransactionsAborted(List<Long> transactionAbortedID);

}
