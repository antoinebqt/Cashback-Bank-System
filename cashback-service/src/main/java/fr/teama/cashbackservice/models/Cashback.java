package fr.teama.cashbackservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cashback {

    @Id
    @GeneratedValue
    private Long id;

    private Long bankAccountId;

    private double amount;

    private Long transactionId;

    private String siret;


    public Cashback(Long bankAccountId, double amount, Long transactionId, String siret) {
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.siret = siret;
    }

    public Cashback() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    @Override
    public String toString() {
        return "Cashback{" +
                "id=" + id +
                ", bankAccountId=" + bankAccountId +
                ", amount=" + amount +
                ", transactionId=" + transactionId +
                ", siret='" + siret + '\'' +
                '}';
    }
}
