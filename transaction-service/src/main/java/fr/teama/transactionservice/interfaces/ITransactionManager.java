package fr.teama.transactionservice.interfaces;

import fr.teama.transactionservice.exceptions.BankAccountNotFoundException;
import fr.teama.transactionservice.exceptions.InvalidCardException;
import fr.teama.transactionservice.exceptions.PaymentFailedException;
import fr.teama.transactionservice.models.Card;
import fr.teama.transactionservice.models.account.BankAccount;
import fr.teama.transactionservice.models.transaction.Transaction;

import java.util.List;

public interface ITransactionManager {
    Transaction pay(Long bankAccountId, String MID, double amount) throws PaymentFailedException;
    List<Transaction> getTransactions();

    BankAccount getBankAccountByCard(Card bankUserCard) throws InvalidCardException, BankAccountNotFoundException;
}
