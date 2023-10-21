package fr.teama.bankservice.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Payment {
    private double amount;
    private String beneficiary;


    public Payment() {
    }
    public Payment (double amount, String beneficiary) {
        this.amount = amount;
        this.beneficiary = beneficiary;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", beneficiary='" + beneficiary + '\'' +
                '}';
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }
}
