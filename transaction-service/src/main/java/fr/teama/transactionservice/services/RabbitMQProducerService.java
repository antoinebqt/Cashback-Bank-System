package fr.teama.transactionservice.services;

import fr.teama.transactionservice.helpers.LoggerHelper;
import fr.teama.transactionservice.models.BalanceMessage;
import fr.teama.transactionservice.models.TransactionMessage;
import fr.teama.transactionservice.models.transaction.Transaction;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean sendTransactionMessage(Transaction transaction, boolean republishing) {
        LoggerHelper.logInfo("Sending transaction message: " + transaction);
        try{
            rabbitTemplate.convertAndSend("transaction-created-queue", new TransactionMessage(transaction,republishing));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean sendBalanceMessage(Long bankAccountId, double amount, Long transactionId, boolean republishing) {
        BalanceMessage balanceMessage = new BalanceMessage(bankAccountId, amount, transactionId,republishing);
        LoggerHelper.logInfo("Sending balance message: " + balanceMessage);
        try{
            rabbitTemplate.convertAndSend("balance-queue-payment", balanceMessage);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
