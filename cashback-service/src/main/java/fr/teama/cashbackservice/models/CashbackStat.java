package fr.teama.cashbackservice.models;

public class CashbackStat {

    private String name;

    private int numberOfTransactions;

    private Double amountSpent;

    private Double cashBackReturned;

    public CashbackStat(String name, int numberOfTransactions, Double amountSpent, Double cashBackReturned) {
        this.name = name;
        this.numberOfTransactions = numberOfTransactions;
        this.amountSpent = amountSpent;
        this.cashBackReturned = cashBackReturned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public Double getCashBackReturned() {
        return cashBackReturned;
    }

    public void setCashBackReturned(Double cashBackReturned) {
        this.cashBackReturned = cashBackReturned;
    }
}
