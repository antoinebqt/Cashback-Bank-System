package fr.teama.cashbackservice.models;

public class CashbackStat {

    private String siret;

    private int numberOfTransactions;

    private Double amountSpent;

    private Double cashBackReturned;

    public CashbackStat(String siret, int numberOfTransactions, Double amountSpent, Double cashBackReturned) {
        this.siret = siret;
        this.numberOfTransactions = numberOfTransactions;
        this.amountSpent = amountSpent;
        this.cashBackReturned = cashBackReturned;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
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
