package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.exceptions.BankUserWithEmailAlreadyExistException;
import fr.teama.bankservice.models.BankUser;

public interface UserRegistration {

    BankUser registerUser(String firstName, String lastName, String email, String password) throws BankUserWithEmailAlreadyExistException;
}
