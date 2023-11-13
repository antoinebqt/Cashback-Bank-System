package fr.teama.bankservice.connectors;

import fr.teama.bankservice.connectors.externalDTO.AffiliatedStoreDTO;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.proxy.ICashbackProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CashbackProxy implements ICashbackProxy {
    @Value("${cashback.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    public Double getCashbackRate(String siret) {
        try {
            LoggerHelper.logInfo("Ask cashback-service for the cashback of merchant with siret " + siret);
            AffiliatedStoreDTO affiliatedStoreDTO = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/store/siret/" + siret, AffiliatedStoreDTO.class).getBody();
            if (affiliatedStoreDTO == null) {
                LoggerHelper.logInfo("The cashback affiliated store founded for siret " + siret);
                return 0.0;
            } else {
                return affiliatedStoreDTO.getCashbackRate();
            }
        } catch (Exception e) {
            LoggerHelper.logError("Cashback service is unavailable");
            return 0.0;
        }
    }
}
