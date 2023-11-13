package fr.teama.cashbackservice.connectors.externalDTO;


public class PaymentDTO {
    private double amount;
    private String beneficiary;

    private String mastercardTransactionId;


    public PaymentDTO() {
    }

    public PaymentDTO(double amount, String beneficiary) {
        this.amount = amount;
        this.beneficiary = beneficiary;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", beneficiary='" + beneficiary + '\'' +
                '}';
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getMastercardTransactionId() {
        return mastercardTransactionId;
    }

    public void setMastercardTransactionId(String mastercardTransactionId) {
        this.mastercardTransactionId = mastercardTransactionId;
    }
}
