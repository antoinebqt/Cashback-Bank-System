package fr.teama.accountservice.interfaces;

import fr.teama.accountservice.exceptions.BankAccountNotFoundException;

public interface BalanceChecker {
    boolean checkBalance(Long bankAccountId, Double amount) throws BankAccountNotFoundException;
}
