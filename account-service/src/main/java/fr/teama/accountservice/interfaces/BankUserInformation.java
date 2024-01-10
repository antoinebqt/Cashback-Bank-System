package fr.teama.accountservice.interfaces;

import fr.teama.accountservice.controllers.dto.CardDTO;
import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.exceptions.InvalidAccountPasswordException;
import fr.teama.accountservice.exceptions.InvalidCardException;
import fr.teama.accountservice.models.BankAccount;
import fr.teama.accountservice.models.BankUser;

public interface BankUserInformation {
    BankUser getBankUser(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
    BankAccount getBankAccountByCard(CardDTO bankUserCard) throws InvalidCardException, BankAccountNotFoundException;
}
