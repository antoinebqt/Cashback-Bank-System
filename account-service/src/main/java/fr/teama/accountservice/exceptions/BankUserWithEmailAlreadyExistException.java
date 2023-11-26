package fr.teama.accountservice.exceptions;

public class BankUserWithEmailAlreadyExistException extends Exception {
    String email;

    public BankUserWithEmailAlreadyExistException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
