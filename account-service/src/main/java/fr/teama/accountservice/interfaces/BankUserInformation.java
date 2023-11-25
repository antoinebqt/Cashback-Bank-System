package fr.teama.accountservice.interfaces;

import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.exceptions.InvalidAccountPasswordException;
import fr.teama.accountservice.models.BankUser;
import fr.teama.accountservice.models.Transaction;

import java.util.List;

public interface BankUserInformation {
    List<Transaction> getTransactions(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
    BankUser getBankUser(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
}
