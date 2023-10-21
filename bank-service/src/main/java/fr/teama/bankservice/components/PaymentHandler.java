package fr.teama.bankservice.components;

import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.IPayment;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentHandler implements IPayment {

    @Override
    public Payment pay(Card card, String beneficiary, double amount) throws NotEnoughMoneyException {
        LoggerHelper.logInfo("Payment of " + amount + " accepted by " + beneficiary);
        if (card.getBankAccount().getBalance() < amount) {
            LoggerHelper.logInfo("Not enough money on account");
            throw new NotEnoughMoneyException();
        }
        card.getBankAccount().setBalance(card.getBankAccount().getBalance() - amount);
        return new Payment(amount, beneficiary);
    }
}
