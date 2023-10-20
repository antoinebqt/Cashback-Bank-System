package fr.teama.bankservice.components;

import fr.teama.bankservice.interfaces.IPayment;
import fr.teama.bankservice.interfaces.ITransaction;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionManager implements ITransaction {
    @Autowired
    private IPayment payment;
    @Override
    public Transaction pay(Card card) {
        return new Transaction(payment.pay(card),card);
    }
}
