package fr.teama.balanceservice.interfaces;

import fr.teama.balanceservice.controllers.dto.CardDTO;
import fr.teama.balanceservice.exceptions.BankAccountNotFoundException;
import fr.teama.balanceservice.exceptions.InvalidAccountPasswordException;
import fr.teama.balanceservice.exceptions.InvalidCardException;
import fr.teama.balanceservice.models.BankAccount;
import fr.teama.balanceservice.models.BankUser;

public interface BankUserInformation {
    BankUser getBankUser(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException;
    BankAccount getBankAccountByCard(CardDTO bankUserCard) throws InvalidCardException, BankAccountNotFoundException;
}
