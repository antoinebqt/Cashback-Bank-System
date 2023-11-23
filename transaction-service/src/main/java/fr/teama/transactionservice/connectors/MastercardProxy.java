package fr.teama.transactionservice.connectors;

import fr.teama.transactionservice.connectors.externalDTO.MastercardPaymentDTO;
import fr.teama.transactionservice.exceptions.TransferPaymentErrorException;
import fr.teama.transactionservice.helpers.LoggerHelper;
import fr.teama.transactionservice.interfaces.proxy.IMastercardProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MastercardProxy implements IMastercardProxy {
    @Value("${mastercard.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String transferPayment(String MID, double amount) throws TransferPaymentErrorException {
        try {
            LoggerHelper.logInfo("Ask Mastercard service to transfer payment");
            MastercardPaymentDTO mastercardPaymentDTO = new MastercardPaymentDTO(MID, amount);
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
