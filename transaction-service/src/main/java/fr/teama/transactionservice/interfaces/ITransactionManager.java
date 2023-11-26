package fr.teama.transactionservice.interfaces;

import fr.teama.transactionservice.exceptions.PaymentFailedException;
import fr.teama.transactionservice.models.Transaction;

import java.util.List;

public interface ITransactionManager {
    Transaction pay(Long bankAccountId, String MID, double amount) throws PaymentFailedException;
    List<Transaction> getTransactions();
}
