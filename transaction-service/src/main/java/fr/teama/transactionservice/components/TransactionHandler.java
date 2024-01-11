package fr.teama.transactionservice.components;


import fr.teama.transactionservice.exceptions.BankAccountUnavailableException;
import fr.teama.transactionservice.exceptions.NotEnoughMoneyException;
import fr.teama.transactionservice.exceptions.PaymentFailedException;
import fr.teama.transactionservice.exceptions.TransferPaymentErrorException;
import fr.teama.transactionservice.helpers.LoggerHelper;
import fr.teama.transactionservice.interfaces.IAccountProxy;
import fr.teama.transactionservice.interfaces.ITransactionManager;
import fr.teama.transactionservice.interfaces.proxy.IMastercardProxy;
import fr.teama.transactionservice.models.transaction.Transaction;
import fr.teama.transactionservice.repository.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionHandler implements ITransactionManager {
    @Autowired
    IMastercardProxy mastercardProxy;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    IAccountProxy bankAccountProxy;

    @Override
    public Transaction pay(Long bankAccountId, String MID, double amount) throws PaymentFailedException {
        try {
            LoggerHelper.logInfo("Check balance for bank account " + bankAccountId);
            if (!bankAccountProxy.checkBalance(bankAccountId, amount)) {
                throw new NotEnoughMoneyException();
            }
            LoggerHelper.logInfo("Transfer fonds (" + amount + ") via mastercard service for merchant " + MID);
            String mastercardTransactionID = mastercardProxy.transferPayment(MID, amount);
            return new Transaction(bankAccountId, amount, MID, mastercardTransactionID);
        } catch (TransferPaymentErrorException e) {
            LoggerHelper.logError("Transfer payment failed");
            throw new PaymentFailedException();
        } catch (BankAccountUnavailableException e) {
            LoggerHelper.logError("Bank account service is unavailable");
            throw new PaymentFailedException();
        } catch (NotEnoughMoneyException e) {
            LoggerHelper.logError("Not enough money in bank account");
            throw new PaymentFailedException();
        }
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

}
