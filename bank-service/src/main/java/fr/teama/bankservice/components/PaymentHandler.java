package fr.teama.bankservice.components;

import fr.teama.bankservice.interfaces.IPayment;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentHandler implements IPayment {

    @Override
    public Payment pay(Card card, String beneficiary, double amount) {
        return new Payment(amount, beneficiary);
    }
}
