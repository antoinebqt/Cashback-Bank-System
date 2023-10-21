package fr.teama.bankservice.components;

import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.exceptions.BankUserWithEmailAlreadyExistException;
import fr.teama.bankservice.exceptions.InvalidAccountPasswordException;
import fr.teama.bankservice.interfaces.BankUserInformation;
import fr.teama.bankservice.interfaces.UserRegistration;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.BankUser;
import fr.teama.bankservice.models.Transaction;
import fr.teama.bankservice.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class BankAccountManager implements UserRegistration, BankUserInformation {

    @Autowired
    BankUserRepository bankUserRepository;

    @Override
    public BankUser registerUser(String firstName, String lastName, String email, String password) throws BankUserWithEmailAlreadyExistException {
        if (bankUserRepository.findByEmail(email) != null) {
            throw new BankUserWithEmailAlreadyExistException();
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
    public List<Transaction> getTransactions(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException {
        BankUser user = getBankUser(email, password);
        return user.getBankAccount().getCard().getTransactions();
    }

    @Override
    public Double getAmountEarnedWithTransactionCashback(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException {
        BankUser user = getBankUser(email, password);
        Double totalEarned = 0.0;
        for (Transaction transaction : user.getBankAccount().getCard().getTransactions()) {
            if (transaction.getCashBack() != null) {
                totalEarned += transaction.getCashBack();
            }
        }
        return totalEarned;
    }
}
