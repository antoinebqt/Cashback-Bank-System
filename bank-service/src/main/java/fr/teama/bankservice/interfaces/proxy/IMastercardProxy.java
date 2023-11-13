package fr.teama.bankservice.interfaces.proxy;

import fr.teama.bankservice.connectors.externalDTO.MastercardPaymentDTO;
import fr.teama.bankservice.exceptions.TransferPaymentErrorException;

public interface IMastercardProxy {
    String transferPayment(MastercardPaymentDTO mastercardPaymentDTO) throws TransferPaymentErrorException;
}
