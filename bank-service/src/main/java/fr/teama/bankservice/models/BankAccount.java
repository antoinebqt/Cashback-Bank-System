package fr.teama.bankservice.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class BankAccount {

    @Embedded
    private Card card;

    private Double balance;

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

    @Override
    public String toString() {
        return "BankAccount{" +
                "card=" + card +
                ", balance=" + balance +
                '}';
    }
}
