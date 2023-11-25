package fr.teama.cashbackservice.services.dto;

public class Transaction {

    private Long id;

    private Long bankAccountId;

    private double amount;

    private String MID;

    private String mastercardTransactionId;

    public Transaction(Long id, Long bankAccountId, double amount, String MID, String mastercardTransactionId) {
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.MID = MID;
        this.mastercardTransactionId = mastercardTransactionId;
    }

    public Transaction() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", bankAccountId=" + bankAccountId +
                ", amount=" + amount +
                ", MID='" + MID + '\'' +
                ", mastercardTransactionId='" + mastercardTransactionId + '\'' +
                '}';
    }
}
