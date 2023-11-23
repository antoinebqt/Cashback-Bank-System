package fr.teama.transactionservice.interfaces;

import fr.teama.transactionservice.models.Transaction;

public interface ITransactionSaver {
    void debitAndSaveTransaction(Transaction transaction);
}
