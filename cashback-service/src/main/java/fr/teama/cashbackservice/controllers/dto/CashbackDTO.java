package fr.teama.cashbackservice.controllers.dto;

import fr.teama.cashbackservice.models.Cashback;

public class CashbackDTO {

    private double amountSpent;

    private double amountReturned;

    private String siret;

    public CashbackDTO(double amountSpent, double amountReturned, String siret) {
        this.amountSpent = amountSpent;
        this.amountReturned = amountReturned;
        this.siret = siret;
    }

    public CashbackDTO() {
    }

    public CashbackDTO(Cashback cashback) {
        this.amountSpent = cashback.getAmountSpent();
        this.amountReturned = cashback.getAmountReturned();
        this.siret = cashback.getSiret();
    }

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amount) {
        this.amountReturned = amount;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    @Override
    public String toString() {
        return "CashbackDTO{" +
                "amountSpent=" + amountSpent +
                ", amountReturned=" + amountReturned +
                ", siret=" + siret +
                '}';
    }
}
