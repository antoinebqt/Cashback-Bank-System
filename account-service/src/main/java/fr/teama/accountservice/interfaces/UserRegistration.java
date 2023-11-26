package fr.teama.accountservice.interfaces;

import fr.teama.accountservice.exceptions.BankUserWithEmailAlreadyExistException;
import fr.teama.accountservice.models.BankUser;

public interface UserRegistration {

    BankUser registerUser(String firstName, String lastName, String email, String password) throws BankUserWithEmailAlreadyExistException;
}
