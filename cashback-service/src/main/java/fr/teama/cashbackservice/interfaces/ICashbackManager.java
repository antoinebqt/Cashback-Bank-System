package fr.teama.cashbackservice.interfaces;

import fr.teama.cashbackservice.exceptions.BadMIDException;
import fr.teama.cashbackservice.exceptions.MIDIterpreterServiceUnavailableException;
import fr.teama.cashbackservice.services.dto.Transaction;

public interface ICashbackManager {

    void processTransaction(Transaction transaction) throws MIDIterpreterServiceUnavailableException, BadMIDException;

    void cancelCashbackTransaction(String transactionId);
}
