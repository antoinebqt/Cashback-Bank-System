package fr.teama.bankservice.components;

import fr.teama.bankservice.interfaces.UserRegistration;
import fr.teama.bankservice.models.Card;

public class BankAccountManager implements UserRegistration {
    @Override
    public Card registerUser(String firstName, String lastName, String email, String password) {
        return null;
    }
}
