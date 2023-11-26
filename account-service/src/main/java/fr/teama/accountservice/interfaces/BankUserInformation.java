package fr.teama.accountservice.interfaces;

import fr.teama.accountservice.controllers.dto.CardDTO;
import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.exceptions.InvalidAccountPasswordException;
import fr.teama.accountservice.exceptions.InvalidCardException;
import fr.teama.accountservice.models.BankAccount;
import fr.teama.accountservice.models.BankUser;
import fr.teama.accountservice.models.Card;
import fr.teama.accountservice.models.Transaction;

import java.util.List;

public interface BankUserInformation {
    List<Transaction> getTransactions(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
    BankUser getBankUser(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
    BankAccount getBankAccountByCard(CardDTO bankUserCard) throws InvalidCardException, BankAccountNotFoundException;
}
