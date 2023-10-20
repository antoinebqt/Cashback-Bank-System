package fr.teama.bankservice.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Payment {
    private double amount;
    private String beneficiary;

    private BankUser user;

    public Payment() {
    }
    public Payment (double amount, String beneficiary) {
        this.amount = amount;
        this.beneficiary = beneficiary;
    }
}
