package fr.teama.bankservice.connectors;

import fr.teama.bankservice.connectors.externalDTO.MastercardPaymentDTO;
import fr.teama.bankservice.exceptions.BadMIDException;
import fr.teama.bankservice.exceptions.MIDIterpreterServiceUnavailableException;
import fr.teama.bankservice.exceptions.MastercardServiceUnavailableException;
import fr.teama.bankservice.exceptions.TransferPaymentErrorException;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.proxy.IMastercardProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MastercardProxy implements IMastercardProxy {
    @Value("${mastercard.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String transferPayment(MastercardPaymentDTO mastercardPaymentDTO) throws TransferPaymentErrorException {
        try {
            LoggerHelper.logInfo("Ask Mastercard service to transfer payment");
            String mastercardTransactionId = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/transaction-payment", mastercardPaymentDTO, String.class).getBody();
            if (mastercardTransactionId == null) {
                throw new TransferPaymentErrorException();
            }
            return mastercardTransactionId;
        } catch (Exception e) {
            LoggerHelper.logError("Mastercard service is unavailable");
            throw new TransferPaymentErrorException();
        }
    }
}
