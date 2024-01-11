package fr.teama.transactionservice.models;

public class BalanceMessage {
    private Long bankAccountId;
    private double amount;
    private Long transactionId;

    public BalanceMessage() {
    }

    public BalanceMessage(Long bankAccountId, double amount, Long transactionId) {
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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
}
