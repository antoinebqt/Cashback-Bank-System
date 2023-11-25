package fr.teama.accountservice.connectors.externalDTO;

public class MastercardPaymentDTO {
    String mid;
    double amount;

    public MastercardPaymentDTO(String mid, double amount) {
        this.mid = mid;
        this.amount = amount;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
