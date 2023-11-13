package fr.teama.bankservice.components;

import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.exceptions.PaymentFailedException;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.IPayment;
import fr.teama.bankservice.interfaces.ITransaction;
import fr.teama.bankservice.interfaces.proxy.ICashbackProxy;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Payment;
import fr.teama.bankservice.models.Transaction;
import fr.teama.bankservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionHandler implements ITransaction {
    @Autowired
    private IPayment payment;
    @Autowired
    ICashbackProxy cashbackProxy;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BalanceManager balanceManager;

    @Override
    public Transaction pay(Card card, String MID, double amount) throws PaymentFailedException {
        LoggerHelper.logInfo("Transfer fonds (" + amount + ") via mastercard service for merchant " + MID);
        Payment myPayment = payment.pay(card, MID, amount);
        LoggerHelper.logInfo("Check if " + myPayment.getSiret() + " is the siret of an affiliated store");
        Double cashbackRate = cashbackProxy.getCashbackRate(myPayment.getSiret());
        // Generate transaction with cashback if affiliated store
        Transaction transaction;
        if (cashbackRate > 0) {
            LoggerHelper.logInfo(myPayment.getSiret() + " is an affiliated store, apply cashback of " + cashbackRate + "%");
            Double cashbackAmount = amount * cashbackRate / 100;
            transaction = new Transaction(cashbackAmount, myPayment, card);
            try {
                balanceManager.addBalance(card.getBankAccount().getIban(), cashbackAmount);
            } catch (Exception e) {
                LoggerHelper.logError("Error while adding cashback to bank account");
            }
        } else {
            LoggerHelper.logInfo(myPayment.getSiret() + " is not an affiliated store");
            transaction = new Transaction(myPayment, card);
        }
        return saveTransaction(transaction);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        LoggerHelper.logInfo("Save transaction");
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getCashbackTransactions() {
        return transactionRepository.findAll().stream().filter(transaction -> transaction.getCashBack() != 0).toList();
    }
}
