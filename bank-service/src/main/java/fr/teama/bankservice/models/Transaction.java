package fr.teama.bankservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Transaction {
    @GeneratedValue
    @Id
    private Long id;
    @Column(nullable = true)
    private Double cashBack;
    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;
    @Embedded
    Payment payment;

    public Transaction() {
    }
    public Transaction(Double cashBack, Payment payment, Card card) {
        this.cashBack = cashBack;
        this.payment = payment;
        this.card = card;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cashBack=" + cashBack +
                ", payment=" + payment +
                '}';
    }

    public Transaction(Payment payment, Card card) {
        this.cashBack = 0.0;
        this.payment = payment;
        this.card = card;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getCashBack() {
        return cashBack;
    }

    public void setCashBack(Double cashBack) {
        this.cashBack = cashBack;
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
