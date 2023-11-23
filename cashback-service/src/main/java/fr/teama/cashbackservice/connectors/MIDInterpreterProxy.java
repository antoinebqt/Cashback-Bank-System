package fr.teama.cashbackservice.connectors;

import fr.teama.cashbackservice.exceptions.BadMIDException;
import fr.teama.cashbackservice.exceptions.MIDIterpreterServiceUnavailableException;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.proxy.IMIDIterpreterProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MIDInterpreterProxy implements IMIDIterpreterProxy {
    @Value("${mid.interpreter.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getSiretByMID(String MID) throws MIDIterpreterServiceUnavailableException, BadMIDException {
        try {
            LoggerHelper.logInfo("Ask MID interpreter service for the siret of " + MID);
            ResponseEntity<String> siretBody = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/siret/" + MID, String.class);
            if (!siretBody.getStatusCode().is2xxSuccessful()) {
                throw new BadMIDException();
            }
            return siretBody.getBody();
        } catch (BadMIDException e) {
            LoggerHelper.logInfo("The MID interpreter service returned an error");
            throw e;
        } catch (Exception e) {
            LoggerHelper.logError("MID interpreter service is unavailable");
            throw new MIDIterpreterServiceUnavailableException();
        }
    }
}
