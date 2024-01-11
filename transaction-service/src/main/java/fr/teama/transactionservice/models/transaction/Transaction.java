package fr.teama.transactionservice.models.transaction;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @GeneratedValue
    @Id
    private Long id;
    private Long bankAccountId;
    private double amount;
    private String MID;
    private String mastercardTransactionId;

    public Transaction() {
    }

    public Transaction(Long bankAccountId, double amount, String MID, String mastercardTransactionId) {
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.MID = MID;
        this.mastercardTransactionId = mastercardTransactionId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", bankAccountId=" + bankAccountId +
                ", amount=" + amount +
                ", MID='" + MID + '\'' +
                ", mastercardTransactionId='" + mastercardTransactionId + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getMastercardTransactionId() {
        return mastercardTransactionId;
    }

    public void setMastercardTransactionId(String mastercardTransactionId) {
        this.mastercardTransactionId = mastercardTransactionId;
    }
}
