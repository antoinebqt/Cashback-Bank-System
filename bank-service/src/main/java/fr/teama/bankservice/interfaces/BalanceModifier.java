package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.models.BankAccount;

public interface BalanceModifier {

    void addBalance(String iban, Double amount) throws BankAccountNotFoundException;

    void debitBalance(BankAccount bankAccount, Double amount) throws NotEnoughMoneyException;

    void addCashback(Long bankAccountId, Double cashbackAmount);

    void removeCashback(Long bankAccountId, Double cashbackToRemove);
}
