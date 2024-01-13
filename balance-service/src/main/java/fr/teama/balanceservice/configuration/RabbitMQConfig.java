package fr.teama.balanceservice.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${balance_payment.queue.name}")
    String balanceQueuePaymentName;
    @Value("${balance_cashback.queue.name}")
    String balanceQueueCashbackName;


    @Bean
    public Queue balanceQueuePayment() {
        return new Queue(balanceQueuePaymentName);
    }
    @Bean
    public Queue balanceQueueCashback() {
        return new Queue(balanceQueueCashbackName);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

