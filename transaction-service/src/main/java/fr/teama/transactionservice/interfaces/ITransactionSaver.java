package fr.teama.transactionservice.interfaces;

import fr.teama.transactionservice.models.transaction.Transaction;

public interface ITransactionSaver {
    void debitAndSaveTransaction(Transaction transaction);
}
