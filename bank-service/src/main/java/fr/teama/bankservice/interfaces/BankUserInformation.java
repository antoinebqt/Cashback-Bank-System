package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.exceptions.InvalidAccountPasswordException;
import fr.teama.bankservice.models.BankUser;
import fr.teama.bankservice.models.Transaction;

import java.util.List;

public interface BankUserInformation {
    List<Transaction> getTransactions(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
    Double getAmountEarnedWithTransactionCashback(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
    BankUser getBankUser(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
}
