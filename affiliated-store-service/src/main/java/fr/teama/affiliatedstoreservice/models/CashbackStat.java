package fr.teama.affiliatedstoreservice.models;

public class CashbackStat {

    private String name;

    private int numberOfCashbackTransactions;

    private Double amountSpent;

    private Double cashBackReturned;

    public CashbackStat(String name, int numberOfCashbackTransactions, Double amountSpent, Double cashBackReturned) {
        this.name = name;
        this.numberOfCashbackTransactions = numberOfCashbackTransactions;
        this.amountSpent = amountSpent;
        this.cashBackReturned = cashBackReturned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCashbackTransactions() {
        return numberOfCashbackTransactions;
    }

    public void setNumberOfCashbackTransactions(int numberOfCashbackTransactions) {
        this.numberOfCashbackTransactions = numberOfCashbackTransactions;
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

    @Override
    public String toString() {
        return "CashbackStat{" +
                "name='" + name + '\'' +
                ", numberOfCashbackTransactions=" + numberOfCashbackTransactions +
                ", amountSpent=" + amountSpent +
                ", cashBackReturned=" + cashBackReturned +
                '}';
    }
}
