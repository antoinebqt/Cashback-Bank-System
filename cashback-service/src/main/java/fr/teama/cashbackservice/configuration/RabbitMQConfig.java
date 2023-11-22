package fr.teama.cashbackservice.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${cashback.queue.name}")
    String cashbackQueueName;

    @Bean
    public Queue myQueue() {
        return new Queue(cashbackQueueName);
    }
}

