package fr.teama.bankservice.components;

import fr.teama.bankservice.interfaces.UserRegistration;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.BankUser;
import fr.teama.bankservice.repository.BankDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BankAccountManager implements UserRegistration {

    @Autowired
    BankDataRepository bankDataRepository;

    @Override
    public Card registerUser(BankUser user) {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder(16);
        StringBuilder cvv = new StringBuilder(3);
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }
        bankDataRepository.save(user);
        return new Card(cardNumber.toString(), "12/24", cvv.toString());
    }
}
