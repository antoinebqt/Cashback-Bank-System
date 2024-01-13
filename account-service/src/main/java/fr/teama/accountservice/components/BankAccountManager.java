package fr.teama.accountservice.components;

import fr.teama.accountservice.controllers.dto.CardDTO;
import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.exceptions.BankUserWithEmailAlreadyExistException;
import fr.teama.accountservice.exceptions.InvalidAccountPasswordException;
import fr.teama.accountservice.exceptions.InvalidCardException;
import fr.teama.accountservice.interfaces.BankUserInformation;
import fr.teama.accountservice.interfaces.UserRegistration;
import fr.teama.accountservice.models.BankAccount;
import fr.teama.accountservice.models.BankUser;
import fr.teama.accountservice.models.Card;
import fr.teama.accountservice.repository.BankAccountRepository;
import fr.teama.accountservice.repository.BankUserRepository;
import fr.teama.accountservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BankAccountManager implements BankUserInformation {

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CardRepository cardRepository;



    @Override
    public BankUser getBankUser(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException {
        BankUser user = bankUserRepository.findByEmail(email);
        if (user == null) {
            throw new BankAccountNotFoundException();
        } else if (!user.getPassword().equals(password)) {
            throw new InvalidAccountPasswordException(email);
        }
        return user;
    }

    @Override
    public BankAccount getBankAccountByCard(CardDTO bankUserCard) throws InvalidCardException, BankAccountNotFoundException {
        Card card = cardRepository.findByCardNumber(bankUserCard.getCardNumber());
        if (!card.getCvv().equals(bankUserCard.getCvv()) && !card.getExpirationDate().equals(bankUserCard.getExpirationDate())) {
            throw new InvalidCardException();
        }
        BankAccount account = card.getBankAccount();
        if (account == null) {
            throw new BankAccountNotFoundException();
        }
        return account;
    }

}
