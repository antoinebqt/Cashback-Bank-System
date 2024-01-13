package fr.teama.cashbackservice.components;

import fr.teama.cashbackservice.interfaces.ICashbackRepublisher;
import fr.teama.cashbackservice.repository.CashbackRepository;
import fr.teama.cashbackservice.services.RabbitMQProducerService;
import fr.teama.cashbackservice.services.dto.BalanceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CashbackRepublisher implements ICashbackRepublisher {
    @Autowired
    RabbitMQProducerService rabbitMQProducerService;
    @Autowired
    CashbackRepository cashbackRepository;

    @Override
    public void republishAllCashbackTransactions() {
        cashbackRepository.findAll().forEach(cashback -> rabbitMQProducerService.sendBalanceMessage(new BalanceMessage(cashback.getBankAccountId(), cashback.getAmountReturned(), cashback.getTransactionId(), true)));
    }

}
