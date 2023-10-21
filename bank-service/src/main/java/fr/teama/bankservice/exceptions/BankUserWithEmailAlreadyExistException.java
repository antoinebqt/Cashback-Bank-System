package fr.teama.bankservice.exceptions;

public class BankUserWithEmailAlreadyExistException extends Exception {
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
