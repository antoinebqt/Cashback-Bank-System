package fr.teama.accountservice.interfaces;

import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.exceptions.NotEnoughMoneyException;
import fr.teama.accountservice.models.BalanceMessage;
import fr.teama.accountservice.models.BankAccount;

public interface BalanceModifier {

    void addBalance(String iban, Double amount) throws BankAccountNotFoundException;

    void changeBalance(BalanceMessage balanceMessage) throws BankAccountNotFoundException;
}
