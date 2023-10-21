package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Payment;

public interface IPayment {
    Payment pay(Card card, String beneficiary, double amount) throws NotEnoughMoneyException;
}
