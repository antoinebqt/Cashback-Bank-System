package fr.teama.accountservice.components;

import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.exceptions.NotEnoughMoneyException;
import fr.teama.accountservice.interfaces.BalanceChecker;
import fr.teama.accountservice.interfaces.BalanceModifier;
import fr.teama.accountservice.models.BalanceMessage;
import fr.teama.accountservice.models.BankAccount;
import fr.teama.accountservice.repository.BalanceMessageRepository;
import fr.teama.accountservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceManager implements BalanceModifier, BalanceChecker {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    BalanceMessageRepository balanceMessageRepository;

    @Override
    public void addBalance(String iban, Double amount) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findByIban(iban);
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("IBAN", iban);
        }
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void changeBalance(BalanceMessage balanceMessage) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(balanceMessage.getBankAccountId()).orElse(null);
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("BankAccountID", balanceMessage.getBankAccountId().toString());
        }
        bankAccount.setBalance(bankAccount.getBalance() + balanceMessage.getAmount());
        bankAccountRepository.save(bankAccount);
        balanceMessageRepository.save(balanceMessage);
    }

    @Override
    public boolean checkBalance(Long bankAccountId, Double amount) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElse(null);
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("BankAccountID", bankAccountId.toString());
        }
        return bankAccount.getBalance() >= amount;
    }
}
