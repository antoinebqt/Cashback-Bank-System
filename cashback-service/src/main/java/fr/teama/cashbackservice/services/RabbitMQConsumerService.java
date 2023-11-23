package fr.teama.cashbackservice.services;

import fr.teama.cashbackservice.controllers.dto.PaymentDTO;
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

    @RabbitListener(queues = "cashback-queue")
    public void receiveMessage(String message) {
        LoggerHelper.logInfo("Received string <" + message + ">");
    }

    @RabbitListener(queues = "object-test-queue")
    public void receiveMessage(PaymentDTO message) {
        LoggerHelper.logInfo("Received payment <" + message + ">");
    }

    @RabbitListener(queues = "transaction-queue")
    public void newTransaction(Transaction transaction) throws MIDIterpreterServiceUnavailableException, BadMIDException {
        LoggerHelper.logInfo("Received transaction: " + transaction);
        this.cashbackManager.processTransaction(transaction);
    }
}
