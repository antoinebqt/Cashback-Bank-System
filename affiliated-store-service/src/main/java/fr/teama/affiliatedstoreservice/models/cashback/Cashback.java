package fr.teama.affiliatedstoreservice.models.cashback;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Cashback {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "bank_account_id")
    private Long bankAccountId;

    @Column(name = "amount_spent")
    private double amountSpent;

    @Column(name = "amount_returned")
    private double amountReturned;

    @Column(name = "transaction_id")
    private Long transactionId;

    private String siret;

    private LocalDateTime timestamp;

    @Column(name = "mastercard_transaction_id")
    private String mastercardTransactionId;


    public Cashback(Long bankAccountId, double amountSpent, double amountReturned, Long transactionId, String siret, String mastercardTransactionId) {
        this.bankAccountId = bankAccountId;
        this.amountSpent = amountSpent;
        this.amountReturned = amountReturned;
        this.transactionId = transactionId;
        this.siret = siret;
        this.timestamp = LocalDateTime.now();
        this.mastercardTransactionId = mastercardTransactionId;
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

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amount) {
        this.amountReturned = amount;
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

    public double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMastercardTransactionId() {
        return mastercardTransactionId;
    }

    public void setMastercardTransactionId(String mastercardTransactionId) {
        this.mastercardTransactionId = mastercardTransactionId;
    }

    @Override
    public String toString() {
        return "Cashback{" +
                "id=" + id +
                ", bankAccountId=" + bankAccountId +
                ", amountSpent=" + amountSpent +
                ", amountReturned=" + amountReturned +
                ", transactionId=" + transactionId +
                ", siret=" + siret +
                ", timestamp=" + timestamp +
                ", mastercardTransactionId='" + mastercardTransactionId + '\'' +
                '}';
    }
}
