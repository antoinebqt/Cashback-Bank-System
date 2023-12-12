package fr.teama.affiliatedstoreservice.models;

public class CashbackDTO {

    private double amountSpent;

    private double amountReturned;

    private String siret;

    private Long transactionId;

    private String mastercardTransactionId;

    public CashbackDTO(double amountSpent, double amountReturned,String siret, Long transactionId, String mastercardTransactionId) {
        this.amountSpent = amountSpent;
        this.amountReturned = amountReturned;
        this.siret = siret;
        this.transactionId = transactionId;
        this.mastercardTransactionId = mastercardTransactionId;
    }

    public CashbackDTO() {
    }

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amount) {
        this.amountReturned = amount;
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getMastercardTransactionId() {
        return mastercardTransactionId;
    }

    public void setMastercardTransactionId(String mastercardTransactionId) {
        this.mastercardTransactionId = mastercardTransactionId;
    }

    @Override
    public String toString() {
        return "CashbackDTO{" +
                "amountSpent=" + amountSpent +
                ", amountReturned=" + amountReturned +
                ", siret=" + siret +
                ", transactionId=" + transactionId +
                ", mastercardTransactionId='" + mastercardTransactionId +
                '}';
    }
}
