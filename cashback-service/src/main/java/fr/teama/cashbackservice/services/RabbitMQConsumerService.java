package fr.teama.cashbackservice.services;

import fr.teama.cashbackservice.exceptions.BadMIDException;
import fr.teama.cashbackservice.exceptions.MIDIterpreterServiceUnavailableException;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.ICashbackManager;
import fr.teama.cashbackservice.services.dto.Transaction;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {

    private final ICashbackManager cashbackManager;

    @Autowired
    RabbitMQConsumerService(ICashbackManager cashbackManager) {
        this.cashbackManager = cashbackManager;
    }

    @RabbitListener(queues = "transaction-created-queue")
    public void newTransaction(Transaction transaction) throws MIDIterpreterServiceUnavailableException, BadMIDException {
        LoggerHelper.logInfo("Received transaction: " + transaction);
        this.cashbackManager.processTransaction(transaction);
    }

    @RabbitListener(queues = "transaction-cancelled-queue")
    public void cancelCashbackOfTransaction(String transactionId) {
        LoggerHelper.logInfo("Received transaction id to cancel cancel cashback: " + transactionId);
        this.cashbackManager.cancelCashbackTransaction(transactionId);
    }
}
