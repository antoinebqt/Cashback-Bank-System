package fr.teama.transactionservice.models;

public class BalanceMessage {
    private Long bankAccountId;
    private double amount;
    private Long transactionId;
    boolean republishing;

    public BalanceMessage() {
    }

    public BalanceMessage(Long bankAccountId, double amount, Long transactionId, boolean republishing) {
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.republishing = republishing;
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

    public void setRepublishing(boolean republishing) {
        this.republishing = republishing;
    }

    public boolean isRepublishing() {
        return republishing;
    }
}
