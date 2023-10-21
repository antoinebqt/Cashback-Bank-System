package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.models.BankUser;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Transaction;

public interface ITransaction {
    Transaction pay(Card card, String beneficiary, double amount) throws NotEnoughMoneyException;
    Transaction saveTransaction(Transaction transaction);
}
