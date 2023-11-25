package fr.teama.affiliatedstoreservice.connectors;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.proxy.IStoreAPIOfType1;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StoreAPIOfType1Proxy implements IStoreAPIOfType1 {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<String> getCashbackTransactionsAbortedID(String apiBaseUrlHostAndPort) {
        try {
            LoggerHelper.logInfo("Ask service for all cashback transactions aborted in the last month");
            ResponseEntity<String[]> response = restTemplate.getForEntity(  apiBaseUrlHostAndPort + "/store/purchaseReturned", String[].class);
            return List.of(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            LoggerHelper.logError("StoreAPI type 1 service is unavailable");
            LoggerHelper.logError(e.getMessage());
            return new ArrayList<>();
        }
    }
}
