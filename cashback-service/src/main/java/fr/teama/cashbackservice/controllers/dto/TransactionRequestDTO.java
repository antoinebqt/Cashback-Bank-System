package fr.teama.cashbackservice.controllers.dto;

public class TransactionRequestDTO {
    private PaymentDTO payment;
    private Long bankAccountId;


    public TransactionRequestDTO() {
    }

    public TransactionRequestDTO(PaymentDTO payment, Long bankAccountId) {
        this.payment = payment;
        this.bankAccountId = bankAccountId;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    @Override
    public String toString() {
        return "TransactionRequestDTO{" +
                "payment=" + payment +
                ", bankAccountId=" + bankAccountId +
                '}';
    }
}