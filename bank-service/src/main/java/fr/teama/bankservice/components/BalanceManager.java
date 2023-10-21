package fr.teama.bankservice.components;

import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.interfaces.BalanceModifier;
import fr.teama.bankservice.models.BankAccount;
import fr.teama.bankservice.repository.BankAccountRepository;
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
}
