package fr.teama.bankservice.components;

import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.IPayment;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentHandler implements IPayment {
    @Autowired
    private BalanceManager balanceManager;
    @Override
    public Payment pay(Card card, String beneficiary, double amount) throws NotEnoughMoneyException {
        balanceManager.debitBalance(card.getBankAccount(), amount);
        LoggerHelper.logInfo("Payment of " + amount + " accepted by " + beneficiary);

        return new Payment(amount, beneficiary);
    }
}
