package fr.teama.cashbackservice.connectors;

import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.proxy.IAffiliatedStoreProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class AffiliatedStoreProxy implements IAffiliatedStoreProxy {
    @Value("${affiliated.store.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public float getCashbackRateFromSiret(String siret) {
        try {
            LoggerHelper.logInfo("Ask affiliated-store service for the cashback rate of the store with siret " + siret);
            ResponseEntity<Float> cashbackRate = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/catalog/" + siret + "/cashback-rate", Float.class);
            return Objects.requireNonNull(cashbackRate.getBody());
        } catch (Exception e) {
            LoggerHelper.logError("Cashback service is unavailable: " + e.getMessage());
            return 0;
        }
    }
}
