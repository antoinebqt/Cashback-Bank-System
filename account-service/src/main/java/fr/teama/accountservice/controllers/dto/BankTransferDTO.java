package fr.teama.accountservice.controllers.dto;

public class BankTransferDTO {

    private String iban;

    private Double amount;

    public BankTransferDTO() {
    }

    public BankTransferDTO(String iban, Double amount) {
        this.iban = iban;
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankTransferDTO{" +
                "iban='" + iban + '\'' +
                ", amount=" + amount +
                '}';
    }
}
