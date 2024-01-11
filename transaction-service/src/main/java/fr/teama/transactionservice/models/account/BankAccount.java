package fr.teama.transactionservice.models.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private BankAccountCard card;

    private String iban;

    private Double balance;

    public BankAccountCard getCard() {
        return card;
    }

    public void setCard(BankAccountCard card) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", card=" + card +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }
}
