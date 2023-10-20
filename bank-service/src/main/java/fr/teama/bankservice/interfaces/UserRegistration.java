package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.BankUser;

public interface UserRegistration {

    Card registerUser(String firstName, String lastName, String email, String password);
}
