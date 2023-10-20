package fr.teama.bankservice.models;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @GeneratedValue
    @Id
    private Long id;
    @Column(nullable = true)
    private Double cashBack;
    @ManyToOne
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
    public Transaction(Payment payment, Card card) {
        this.payment = payment;
        this.card = card;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
