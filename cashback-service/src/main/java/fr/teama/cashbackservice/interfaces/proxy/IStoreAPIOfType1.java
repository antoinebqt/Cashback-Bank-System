package fr.teama.cashbackservice.interfaces.proxy;

import java.util.List;

public interface IStoreAPIOfType1 {

    List<Long> getCashbackTransactionsAbortedID(String apiBaseUrlHostAndPort);
}
