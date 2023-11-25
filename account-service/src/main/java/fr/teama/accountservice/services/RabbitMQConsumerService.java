package fr.teama.accountservice.services;

import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.helpers.LoggerHelper;
import fr.teama.accountservice.interfaces.BalanceModifier;
import fr.teama.accountservice.models.BalanceMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {

    private final BalanceModifier balanceModifier;

    @Autowired
    RabbitMQConsumerService(BalanceModifier balanceModifier) {
        this.balanceModifier = balanceModifier;
    }

    @RabbitListener(queues = "balance-queue")
    public void newTransaction(BalanceMessage balanceMessage) throws BankAccountNotFoundException {
        LoggerHelper.logInfo("Received transaction: " + balanceMessage);
        this.balanceModifier.changeBalance(balanceMessage);
    }
}
