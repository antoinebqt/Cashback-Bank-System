package fr.teama.transactionservice.services;

import fr.teama.transactionservice.helpers.LoggerHelper;
import fr.teama.transactionservice.models.BalanceMessage;
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

    public void sendTransactionMessage(Transaction transaction) {
        LoggerHelper.logInfo("Sending transaction message: " + transaction);
        rabbitTemplate.convertAndSend("transaction-created-queue", transaction);
    }

    public void sendBalanceMessage(Long bankAccountId, double amount, Long transactionId) {
        BalanceMessage balanceMessage = new BalanceMessage(bankAccountId, amount, transactionId);
        LoggerHelper.logInfo("Sending balance message: " + balanceMessage);
        rabbitTemplate.convertAndSend("balance-queue", balanceMessage);
    }
}
