package fr.teama.affiliatedstoreservice.connectors;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.proxy.IStoreAPIOfType2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StoreAPIOfType2Proxy implements IStoreAPIOfType2 {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<String> getCashbackTransactionsAborted(List<String> transactionAbortedID, String apiRoute) {
        try {
            LoggerHelper.logInfo("Ask service if those cashback transactions have been cancelled");
            ResponseEntity<String[]> response = restTemplate.postForEntity(apiRoute + "/store/purchaseReturned", transactionAbortedID, String[].class);
            return List.of(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            LoggerHelper.logError("StoreAPI type 2 service is unavailable");
            LoggerHelper.logError(e.getMessage());
            return new ArrayList<>();
        }
    }
}
