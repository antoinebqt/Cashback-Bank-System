package fr.teama.transactionservice.components;

import fr.teama.transactionservice.interfaces.ITransactionRepublisher;
import fr.teama.transactionservice.repository.TransactionRepository;
import fr.teama.transactionservice.services.RabbitMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionRepublisher implements ITransactionRepublisher{
    @Autowired
    RabbitMQProducerService rabbitMQProducerService;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void republishAllTransactions() {
        transactionRepository.findAll().forEach(transaction -> rabbitMQProducerService.sendBalanceMessage(transaction.getBankAccountId(), -1 * (transaction.getAmount()),transaction.getId(),true));
    }

}
