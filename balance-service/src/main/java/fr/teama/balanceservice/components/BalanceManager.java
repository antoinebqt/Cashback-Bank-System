package fr.teama.balanceservice.components;

import fr.teama.balanceservice.exceptions.BankAccountNotFoundException;
import fr.teama.balanceservice.externalDTO.BalanceMessageDTO;
import fr.teama.balanceservice.interfaces.BalanceModifier;
import fr.teama.balanceservice.models.BalanceModification;
import fr.teama.balanceservice.models.BankAccount;
import fr.teama.balanceservice.repository.BalanceModificationRepository;
import fr.teama.balanceservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BalanceManager implements BalanceModifier {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    BalanceModificationRepository balanceModificationRepository;

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
    public void changeBalance(BalanceMessageDTO balanceMessageDTO, boolean isPayment) throws BankAccountNotFoundException {
        if (balanceMessageDTO.isRepublishing()){
            if (isPayment){
                Optional<BalanceModification> balanceModification = balanceModificationRepository.findByTransactionIdAndCashbackIsFalse(balanceMessageDTO.getTransactionId());
                if (balanceModification.isPresent()){
                    System.out.println("Payment already processed " + balanceModification.get().getTransactionId());
                    return;
                }
            }
            else{
                Optional<BalanceModification> balanceModification = balanceModificationRepository.findByTransactionIdAndCashbackIsTrue(balanceMessageDTO.getTransactionId());
                if (balanceModification.isPresent()&& balanceModification.get().getAmount() == balanceMessageDTO.getAmount()){
                    System.out.println("Cashback already processed " + balanceModification.get().getTransactionId());
                    return;
                }
            }
        }
        BankAccount bankAccount = bankAccountRepository.findById(balanceMessageDTO.getBankAccountId()).orElse(null);
        if (bankAccount == null) {
            System.out.println("no bank account found");
            System.out.println(bankAccountRepository.findAll());
            throw new BankAccountNotFoundException("BankAccountID", balanceMessageDTO.getBankAccountId().toString());
        }
        BalanceModification balanceModification = new BalanceModification(balanceMessageDTO,bankAccount);
        balanceModification.setCashback(!isPayment);
        bankAccount.setBalance(bankAccount.getBalance() + balanceModification.getAmount());
        bankAccountRepository.save(bankAccount);
        balanceModificationRepository.save(balanceModification);
    }
}
