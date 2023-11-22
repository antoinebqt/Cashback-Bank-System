package fr.teama.cashbackservice.services;

import fr.teama.cashbackservice.helpers.LoggerHelper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {
    @RabbitListener(queues = "cashback-queue")
    public void receiveMessage(String message) {
        LoggerHelper.logInfo("Received <" + message + ">");
    }
}
