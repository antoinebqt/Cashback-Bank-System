package fr.teama.accountservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BalanceMessage {
    private Long bankAccountId;
    private double amount;
    @Id
    private Long id;

    public BalanceMessage() {
    }

    public BalanceMessage(Long bankAccountId, double amount) {
        this.bankAccountId = bankAccountId;
        this.amount = amount;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
