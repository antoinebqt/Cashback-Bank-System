package fr.teama.bankservice.components;

import fr.teama.bankservice.controllers.dto.TransactionDTO;
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

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionHandler implements ITransaction {
    @Autowired
    private IPayment payment;
    @Autowired
    ICashbackProxy cashbackProxy;
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public Transaction pay(Card card, String MID, double amount) throws PaymentFailedException {
        LoggerHelper.logInfo("Transfer fonds (" + amount + ") via mastercard service for merchant " + MID);
        Payment myPayment = payment.pay(card, MID, amount);

        Transaction transaction = new Transaction(myPayment, card);

        transaction = cashbackProxy.updateWithPotentialCashback(transaction);

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
    public List<TransactionDTO> getCashbackTransactionsByStore(String siret) {
        List<Transaction> transactions = transactionRepository.findByCashbackReturnedIsNot(0);

        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transaction transaction : transactions) {

            //if (transaction.getPayment().getSiret().equals(siret))
            transactionDTOS.add(new TransactionDTO(transaction));
        }
        return transactionDTOS;
    }
    @Override
    public List<TransactionDTO> getCashbackTransactions() {
        List<Transaction> transactions = transactionRepository.findByCashbackReturnedIsNot(0);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDTOS.add(new TransactionDTO(transaction));
        }
        return transactionDTOS;
    }

    @Override
    public void cancelCashback(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction != null) {
            transaction.setCashbackReturned(0.0);
            transactionRepository.save(transaction);
        }
    }
}
