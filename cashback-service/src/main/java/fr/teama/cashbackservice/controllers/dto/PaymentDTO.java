package fr.teama.cashbackservice.controllers.dto;


public class PaymentDTO {
    private double amount;
    private String siret;
    private String mastercardTransactionId;


    public PaymentDTO() {
    }

    public PaymentDTO(double amount, String siret) {
        this.amount = amount;
        this.siret = siret;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", siret='" + siret + '\'' +
                '}';
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getMastercardTransactionId() {
        return mastercardTransactionId;
    }

    public void setMastercardTransactionId(String mastercardTransactionId) {
        this.mastercardTransactionId = mastercardTransactionId;
    }

}
