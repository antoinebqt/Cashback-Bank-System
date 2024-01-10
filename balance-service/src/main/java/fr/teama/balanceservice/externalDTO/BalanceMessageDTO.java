package fr.teama.balanceservice.externalDTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class BalanceMessageDTO {

    private Long bankAccountId;
    private double amount;
    private Long transactionId;
    @Id
    @GeneratedValue
    private Long id;

    public BalanceMessageDTO() {
    }


    @Override
    public String toString() {
        return "BalanceMessage{" +
                "bankAccountId=" + bankAccountId +
                ", amount=" + amount +
                '}';
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
