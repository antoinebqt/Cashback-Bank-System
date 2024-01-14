package fr.teama.transactionservice.components;

import fr.teama.transactionservice.interfaces.ITransactionRepublisher;
import fr.teama.transactionservice.models.transaction.Transaction;
import fr.teama.transactionservice.repository.transaction.TransactionRepository;
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
    public String republishAllTransactions() {
        for (Transaction transaction: transactionRepository.findAll()) {
            if(!rabbitMQProducerService.sendBalanceMessage(transaction.getBankAccountId(), -1 * (transaction.getAmount()),transaction.getId(),true))
                return "Queue not ready";
            if(!rabbitMQProducerService.sendTransactionMessage(transaction,true))
                return "Queue not ready";
        }
        return "All messages have been sent";
    }

}
