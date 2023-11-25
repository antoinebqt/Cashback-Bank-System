package fr.teama.cashbackservice.services.dto;

public class BalanceMessage {

    private Long bankAccountId;

    private double amount;

    public BalanceMessage(Long bankAccountId, double amount) {
        this.bankAccountId = bankAccountId;
        this.amount = amount;
    }

    public BalanceMessage() {
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

    @Override
    public String toString() {
        return "BalanceMessage{" +
                "bankAccountId=" + bankAccountId +
                ", amount=" + amount +
                '}';
    }
}
