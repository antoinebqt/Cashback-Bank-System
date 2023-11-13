package fr.teama.cashbackservice.connectors;


import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.proxy.IStoreAPIOfType1;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StoreAPIOfType1Proxy implements IStoreAPIOfType1 {

    ///api/store/carrefour/purchaseReturned
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<String> getCashbackTransactionsAbortedID(String apiBaseUrlHostAndPort) {
        try {
            LoggerHelper.logInfo("Ask carrefour service for all cashback transactions aborted in the last month");
            ResponseEntity<String[]> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/api/store/purchaseReturned", String[].class);
            return List.of(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            LoggerHelper.logError("Carrefour service is unavailable");
            LoggerHelper.logError(e.getMessage());
            return new ArrayList<>();
        }
    }
}
