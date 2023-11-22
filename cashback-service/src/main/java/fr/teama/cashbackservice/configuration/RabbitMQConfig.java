package fr.teama.cashbackservice.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    @Bean
    public Queue transactionQueue() {
        return new Queue("object-test-queue");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

