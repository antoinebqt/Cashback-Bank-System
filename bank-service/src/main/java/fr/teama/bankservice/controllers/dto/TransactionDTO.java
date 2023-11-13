package fr.teama.bankservice.controllers.dto;

import fr.teama.bankservice.models.Payment;
import fr.teama.bankservice.models.Transaction;

public class TransactionDTO {

    private Long id;

    private Double cashbackReturned;

    Payment payment;

    Long bankAccountId;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.cashbackReturned = transaction.getCashbackReturned();
        this.payment = transaction.getPayment();
        this.bankAccountId = transaction.getCard().getBankAccount().getId();
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
}
