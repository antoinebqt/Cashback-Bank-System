package fr.teama.cashbackservice.interfaces.proxy;

import fr.teama.cashbackservice.connectors.externalDTO.TransactionDTO;

import java.util.List;

public interface IBankProxy {
    List<TransactionDTO> getCashbackTransactions();

    List<TransactionDTO> getCashbackTransactionsByStore(String siret);

    void addCashback(Double cashbackAmount, Long bankAccountId);

    void removeCashback(Double cashbackToCancel, Long bankAccountId);
}
