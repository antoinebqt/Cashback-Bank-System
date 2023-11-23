package fr.teama.transactionservice.interfaces.proxy;

import fr.teama.transactionservice.connectors.externalDTO.MastercardPaymentDTO;
import fr.teama.transactionservice.exceptions.TransferPaymentErrorException;

public interface IMastercardProxy {
    String transferPayment(String MID, double amount) throws TransferPaymentErrorException;
}
