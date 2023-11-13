package fr.teama.cashbackservice.connectors.externalDTO;

public class TransactionDTO {

    private Long id;

    private Double cashbackReturned;

    PaymentDTO payment;

    public TransactionDTO() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cashBack=" + cashbackReturned +
                ", payment=" + payment +
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
}
