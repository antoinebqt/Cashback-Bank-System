package fr.teama.cashbackservice.services;

import fr.teama.cashbackservice.controllers.dto.PaymentDTO;
import fr.teama.cashbackservice.controllers.dto.TransactionRequestDTO;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {
    @RabbitListener(queues = "cashback-queue")
    public void receiveMessage(String message) {
        LoggerHelper.logInfo("Received string <" + message + ">");
    }

    @RabbitListener(queues = "object-test-queue")
    public void receiveMessage(PaymentDTO message) {
        LoggerHelper.logInfo("Received payment <" + message + ">");
    }
}
