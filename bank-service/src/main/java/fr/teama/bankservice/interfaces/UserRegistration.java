package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.User;

public interface UserRegistration {

    Card registerUser(User user);
}
