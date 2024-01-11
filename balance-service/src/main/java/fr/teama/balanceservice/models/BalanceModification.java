package fr.teama.balanceservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.teama.balanceservice.externalDTO.BalanceMessageDTO;
import jakarta.persistence.*;

@Entity
public class BalanceModification {
    private double amount;
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name = "bankAccount_id")
    @JsonIgnore
    private BankAccount bankAccount;
    @Id
    @GeneratedValue
    private Long id;

    public BalanceModification() {
    }

    public BalanceModification(BalanceMessageDTO balanceMessageDTO, BankAccount bankAccount) {
        this.amount = balanceMessageDTO.getAmount();
        this.transactionId = balanceMessageDTO.getTransactionId();
        this.bankAccount = bankAccount;
    }


    @Override
    public String toString() {
        return "BalanceMessage{" +
                ", amount=" + amount +
                '}';
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}

