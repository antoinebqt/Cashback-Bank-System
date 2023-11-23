package fr.teama.affiliatedstoreservice.connectors;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.proxy.ICashbackProxy;
import fr.teama.affiliatedstoreservice.models.CashbackDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class CashbackProxy implements ICashbackProxy {
    @Value("${cashback.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<CashbackDTO> getCashbackTransactionsLastMonth() {
        try {
            LoggerHelper.logInfo("Ask cashback service for the cashback transactions of the last month");
            ResponseEntity<CashbackDTO[]> cashbacks = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/cashback/last-month", CashbackDTO[].class);
            return List.of(Objects.requireNonNull(cashbacks.getBody()));
        } catch (Exception e) {
            LoggerHelper.logError("Cashback service is unavailable: " + e.getMessage());
            return null;
        }
    }
}
