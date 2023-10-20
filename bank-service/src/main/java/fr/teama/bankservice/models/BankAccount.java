package fr.teama.bankservice.models;

import jakarta.persistence.*;

@Entity
public class BankAccount {
    @OneToOne
    private Card card;

    private String iban;

    private Double balance;
    @Id
    @GeneratedValue
    private Long id;

    public BankAccount() {
        card = new Card();
        balance = 0.0;
    }

    public BankAccount(Card card, Double balance) {
        this.card = card;
        this.balance = balance;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "card=" + card +
                ", balance=" + balance +
                ", iban='" + iban +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
