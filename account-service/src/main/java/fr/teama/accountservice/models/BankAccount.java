package fr.teama.accountservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;
    @OneToOne(mappedBy = "bankAccount")
    @JsonIgnore
    private BankUser bankUser;

    @OneToMany(mappedBy = "bankAccount")
    private List<BalanceModification> balanceModificationList;


    private String iban;

    private Double balance;

    public BankAccount() {
        card = new Card();
        balance = 0.0;
    }

    public BankAccount(BankUser bankUser, Card card, Double balance) {
        this.card = card;
        this.balance = balance;
        this.bankUser = bankUser;
    }

    public List<BalanceModification> getBalanceModificationList() {
        return balanceModificationList;
    }

    public void setBalanceModificationList(List<BalanceModification> balanceModificationList) {
        this.balanceModificationList = balanceModificationList;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BankUser getBankUser() {
        return bankUser;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", card=" + card +
                ", bankUser=" + bankUser +
                ", balanceModificationList=" + balanceModificationList +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }
}
