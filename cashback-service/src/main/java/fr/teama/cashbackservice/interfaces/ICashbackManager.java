package fr.teama.cashbackservice.interfaces;

import fr.teama.cashbackservice.controllers.dto.PaymentDTO;
import fr.teama.cashbackservice.exceptions.BadMIDException;
import fr.teama.cashbackservice.exceptions.MIDIterpreterServiceUnavailableException;
import fr.teama.cashbackservice.services.dto.Transaction;

public interface ICashbackManager {
    Double addPotentialCashback(PaymentDTO payment, Long bankAccountId);

    void processTransaction(Transaction transaction) throws MIDIterpreterServiceUnavailableException, BadMIDException;
}
