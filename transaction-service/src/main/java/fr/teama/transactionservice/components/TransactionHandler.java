package fr.teama.transactionservice.components;


import fr.teama.transactionservice.exceptions.*;
import fr.teama.transactionservice.helpers.LoggerHelper;
import fr.teama.transactionservice.interfaces.IAccountProxy;
import fr.teama.transactionservice.interfaces.ITransactionManager;
import fr.teama.transactionservice.interfaces.proxy.IMastercardProxy;
import fr.teama.transactionservice.models.Card;
import fr.teama.transactionservice.models.account.BankAccount;
import fr.teama.transactionservice.models.account.BankAccountCard;
import fr.teama.transactionservice.models.transaction.Transaction;
import fr.teama.transactionservice.repository.account.AccountRepository;
import fr.teama.transactionservice.repository.account.CardRepository;
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
    CardRepository cardRepository;
    @Autowired
    AccountRepository bankAccountRepository;

    @Override
    public Transaction pay(Long bankAccountId, String MID, double amount) throws PaymentFailedException {
        try {
            LoggerHelper.logInfo("Check balance for bank account " + bankAccountId);
            if (!checkBalance(bankAccountId, amount)) {
                throw new NotEnoughMoneyException();
            }
            LoggerHelper.logInfo("Transfer fonds (" + amount + ") via mastercard service for merchant " + MID);
            String mastercardTransactionID = mastercardProxy.transferPayment(MID, amount);
            return new Transaction(bankAccountId, amount, MID, mastercardTransactionID);
        } catch (TransferPaymentErrorException e) {
            LoggerHelper.logError("Transfer payment failed");
            throw new PaymentFailedException();
        } catch (BankAccountNotFoundException e) {
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

    @Override
    public BankAccount getBankAccountByCard(Card bankUserCard) throws InvalidCardException, BankAccountNotFoundException {
        BankAccountCard card = cardRepository.findByCardNumber(bankUserCard.getCardNumber());
        if (!card.getCvv().equals(bankUserCard.getCvv()) && !card.getExpirationDate().equals(bankUserCard.getExpirationDate())) {
            throw new InvalidCardException();
        }
        BankAccount account = card.getBankAccount();
        if (account == null) {
            throw new BankAccountNotFoundException();
        }
        return account;
    }

    private boolean checkBalance(Long bankAccountId, Double amount) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElse(null);
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("BankAccountID", bankAccountId.toString());
        }
        return bankAccount.getBalance() >= amount;
    }

}
