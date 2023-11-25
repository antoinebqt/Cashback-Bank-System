package fr.teama.accountservice.components;

import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.exceptions.NotEnoughMoneyException;
import fr.teama.accountservice.interfaces.BalanceModifier;
import fr.teama.accountservice.models.BankAccount;
import fr.teama.accountservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceManager implements BalanceModifier {

    @Autowired
    BankAccountRepository bankAccountRepository;

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
    public void debitBalance(BankAccount bankAccount, Double amount) throws NotEnoughMoneyException {
        if (bankAccount.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void addCashback(Long bankAccountId, Double cashbackAmount) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElse(null);
        if (bankAccount != null) {
            bankAccount.setBalance(bankAccount.getBalance() + cashbackAmount);
            bankAccountRepository.save(bankAccount);
        }
    }

    @Override
    public void removeCashback(Long bankAccountId, Double cashbackToRemove) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElse(null);
        if (bankAccount != null) {
            bankAccount.setBalance(bankAccount.getBalance() - cashbackToRemove);
            bankAccountRepository.save(bankAccount);
        }
    }
}
