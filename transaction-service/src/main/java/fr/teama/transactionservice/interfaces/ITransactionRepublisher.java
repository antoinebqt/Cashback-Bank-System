package fr.teama.transactionservice.interfaces;

import fr.teama.transactionservice.models.Transaction;

import java.util.List;

public interface ITransactionRepublisher {
    void republishAllTransactions();
}
