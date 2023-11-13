package fr.teama.bankservice.connectors;

import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.proxy.ICashbackProxy;
import fr.teama.bankservice.models.Payment;
import fr.teama.bankservice.models.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class CashbackProxy implements ICashbackProxy {
    @Value("${cashback.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Transaction updateWithPotentialCashback(Transaction transaction) {
        try {
            LoggerHelper.logInfo("Send transaction to Cashback service");
            Payment payment = transaction.getPayment();
            Long bankAccountId = transaction.getCard().getBankAccount().getId();

            // Créez un objet contenant à la fois la transaction et bankAccountId
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("payment", payment);
            requestBody.put("bankAccountId", bankAccountId);

            Double cashback = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/cashback/check-transaction", requestBody, Double.class).getBody();
            if (cashback != null && cashback > 0.0) {
                LoggerHelper.logInfo("Cashback service found a cashback for this transaction");
                transaction.setCashbackReturned(cashback);
            }
            return transaction;
        } catch (Exception e) {
            LoggerHelper.logError("Cashback service is unavailable");
            return transaction;
        }
    }
}
