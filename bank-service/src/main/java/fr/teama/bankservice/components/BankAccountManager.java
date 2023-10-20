package fr.teama.bankservice.components;

import fr.teama.bankservice.interfaces.UserRegistration;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.BankUser;
import org.springframework.stereotype.Component;

@Component
public class BankAccountManager implements UserRegistration {
    @Override
    public Card registerUser(BankUser user) {
        return null;
    }
}
