package fr.teama.accountservice.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Payment {
    private double amount;
    private String siret;
    private String mastercardTransactionId;


    public Payment() {
    }

    public Payment (double amount, String siret, String mastercardTransactionId) {
        this.amount = amount;
        this.siret = siret;
        this.mastercardTransactionId = mastercardTransactionId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", siret='" + siret + '\'' +
                '}';
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getMastercardTransactionId() {
        return mastercardTransactionId;
    }

    public void setMastercardTransactionId(String mastercardTransactionId) {
        this.mastercardTransactionId = mastercardTransactionId;
    }
}
