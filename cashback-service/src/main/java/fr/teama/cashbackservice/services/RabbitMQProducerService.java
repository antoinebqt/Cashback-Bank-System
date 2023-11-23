package fr.teama.cashbackservice.services;

import fr.teama.cashbackservice.services.dto.BalanceMessage;
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


    public void sendBalanceMessage(BalanceMessage balanceMessage) {
        rabbitTemplate.convertAndSend("balance-queue", balanceMessage);
    }
}
