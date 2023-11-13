package fr.teama.cashbackservice.connectors;

import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.proxy.IStoreAPIOfType2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StoreAPIOfType2Proxy implements IStoreAPIOfType2 {
    @Value("${carrefour.host.baseurl}")
    private String apiBaseUrlHostAndPort;
    ///api/store/carrefour/purchaseReturned
    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public List<Long> getCashbackTransactionsAborted(List<Long> transactionAbortedID) {
        try {
            LoggerHelper.logInfo("Ask carrefour service for all cashback transactions aborted in the last month");
            ResponseEntity<Long[]> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/api/store/purchaseReturned", Long[].class,transactionAbortedID);
            return List.of(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            LoggerHelper.logError("Carrefour service is unavailable");
            LoggerHelper.logError(e.getMessage());
            return new ArrayList<>();
        }
    }
}
