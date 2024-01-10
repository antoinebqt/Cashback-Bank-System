package fr.teama.balanceservice.services;

import fr.teama.balanceservice.exceptions.BankAccountNotFoundException;
import fr.teama.balanceservice.externalDTO.BalanceMessageDTO;
import fr.teama.balanceservice.helpers.LoggerHelper;
import fr.teama.balanceservice.interfaces.BalanceModifier;
import fr.teama.balanceservice.models.BalanceModification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {

    private final BalanceModifier balanceModifier;

    @Autowired
    RabbitMQConsumerService(BalanceModifier balanceModifier) {
        this.balanceModifier = balanceModifier;
    }

    @RabbitListener(queues = "balance-queue")
    public void newTransaction(BalanceMessageDTO balanceMessageDTO) throws BankAccountNotFoundException {
        LoggerHelper.logInfo("Received transaction: " + balanceMessageDTO);
        this.balanceModifier.changeBalance(balanceMessageDTO);
    }
}
