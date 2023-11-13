package fr.teama.cashbackservice.connectors.externalDTO;

public class TransactionDTO {

    private Long id;

    private Double cashbackReturned;

    PaymentDTO payment;

    Long bankAccountId;

    public TransactionDTO() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cashBack=" + cashbackReturned +
                ", payment=" + payment +
                ", bankAccountId=" + bankAccountId +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getCashbackReturned() {
        return cashbackReturned;
    }

    public void setCashbackReturned(Double cashbackReturned) {
        this.cashbackReturned = cashbackReturned;
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
}
