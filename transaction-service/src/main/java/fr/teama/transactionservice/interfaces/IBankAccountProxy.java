package fr.teama.transactionservice.interfaces;

import fr.teama.transactionservice.exceptions.BankAccountUnavailableException;
import fr.teama.transactionservice.models.Card;

public interface IBankAccountProxy {
    Long getBankAccountIdByCard(Card card) throws BankAccountUnavailableException;
    boolean checkBalance(Long bankAccountId, double amount) throws BankAccountUnavailableException;
}
