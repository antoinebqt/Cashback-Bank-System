package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.BankUser;

public interface UserRegistration {

    Card registerUser(BankUser user);
}
