package fr.teama.balanceservice.interfaces;

import fr.teama.balanceservice.exceptions.BankUserWithEmailAlreadyExistException;
import fr.teama.balanceservice.models.BankUser;

public interface UserRegistration {

    BankUser registerUser(String firstName, String lastName, String email, String password) throws BankUserWithEmailAlreadyExistException;
}
