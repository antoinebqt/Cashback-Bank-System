package fr.teama.bankservice.components;

import fr.teama.bankservice.interfaces.UserRegistration;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.BankUser;
import fr.teama.bankservice.repository.BankAccountRepository;
import fr.teama.bankservice.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BankAccountManager implements UserRegistration {

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public Card registerUser(String firstName, String lastName, String email, String password) {
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
        Card card = new Card(cardNumber.toString(), "12/24", cvv.toString(), user.getBankAccount());

        StringBuilder iban = new StringBuilder(22);
        for (int i = 0; i < 22; i++) {
            iban.append(random.nextInt(10));
        }

        user.getBankAccount().setCard(card);
        user.getBankAccount().setIban("FR" + iban + "A12");

        bankUserRepository.save(user);
        return card;
    }
}
