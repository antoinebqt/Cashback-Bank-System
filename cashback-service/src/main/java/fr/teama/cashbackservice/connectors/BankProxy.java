package fr.teama.cashbackservice.connectors;

import fr.teama.cashbackservice.connectors.externalDTO.TransactionDTO;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.proxy.IBankProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BankProxy implements IBankProxy {
    @Value("${bank.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<TransactionDTO> getCashbackTransactions() {
        try {
            LoggerHelper.logInfo("Ask bank-service for all cashback transactions");
            ResponseEntity<TransactionDTO[]> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/transaction/cashback", TransactionDTO[].class);
            return List.of(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            LoggerHelper.logError("Bank service is unavailable");
            LoggerHelper.logError(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void addCashback(Double cashbackAmount, Long bankAccountId) {
        try {
            LoggerHelper.logInfo("Ask Bank service to add " + cashbackAmount + "€ to bank account " + bankAccountId);
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/balance/add-cashback/" + bankAccountId, cashbackAmount, Void.class);
        } catch (Exception e) {
            LoggerHelper.logError("Bank service is unavailable");
            LoggerHelper.logError(e.getMessage());
        }
    }
}
