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
import fr.teama.accountservice.models.Transaction;
import fr.teama.accountservice.repository.BankAccountRepository;
import fr.teama.accountservice.repository.BankUserRepository;
import fr.teama.accountservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class BankAccountManager implements UserRegistration, BankUserInformation {

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public BankUser registerUser(String firstName, String lastName, String email, String password) throws BankUserWithEmailAlreadyExistException {
        if (bankUserRepository.findByEmail(email) != null) {
            throw new BankUserWithEmailAlreadyExistException(email);
        }

        BankUser user = new BankUser(firstName, lastName, email, password);

        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder(16);
        StringBuilder cvv = new StringBuilder(3);
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }
        Card card = new Card(cardNumber.toString(), "12/24", cvv.toString());

        StringBuilder iban = new StringBuilder(22);
        for (int i = 0; i < 22; i++) {
            iban.append(random.nextInt(10));
        }

        user.getBankAccount().setCard(card);
        user.getBankAccount().setIban("FR" + iban + "A12");

        return bankUserRepository.save(user);
    }

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

    @Override
    public List<Transaction> getTransactions(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException {
        //TODO add the new logic
        return null;

        //BankUser user = getBankUser(email, password);
        //return user.getBankAccount().getCard().getTransactions();
    }

}
