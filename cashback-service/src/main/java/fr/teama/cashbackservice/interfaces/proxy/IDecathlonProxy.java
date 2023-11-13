package fr.teama.cashbackservice.interfaces.proxy;

import fr.teama.cashbackservice.connectors.externalDTO.TransactionDTO;

import java.util.List;

public interface IDecathlonProxy {
    List<Long> getCashbackTransactionsAborted(List<Long> transactionAbortedID);

}
