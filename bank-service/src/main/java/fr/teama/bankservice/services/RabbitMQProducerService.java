package fr.teama.bankservice.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${cashback.queue.name}")
    String cashbackQueueName;

    @Autowired
    public RabbitMQProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCashbackMessage(String message) {
        rabbitTemplate.convertAndSend(cashbackQueueName, message);
    }
}
