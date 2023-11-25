package fr.teama.accountservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Transaction {
    @GeneratedValue
    @Id
    private Long id;
    @Column(nullable = true)
    private Double cashbackReturned;
    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;
    @Embedded
    Payment payment;

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cashBack=" + cashbackReturned +
                ", payment=" + payment +
                '}';
    }

    public Transaction(Payment payment, Card card) {
        this.cashbackReturned = 0.0;
        this.payment = payment;
        this.card = card;
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

    public void setCashbackReturned(Double cashBack) {
        this.cashbackReturned = cashBack;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
