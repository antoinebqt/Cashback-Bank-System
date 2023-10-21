package fr.teama.bankservice.interfaces;

import fr.teama.bankservice.exceptions.BankAccountNotFoundException;

public interface BalanceModifier {

    void addBalance(String iban, Double amount) throws BankAccountNotFoundException;
}
