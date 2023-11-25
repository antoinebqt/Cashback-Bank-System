package fr.teama.bankservice.interfaces.proxy;

import fr.teama.bankservice.exceptions.BadMIDException;
import fr.teama.bankservice.exceptions.MIDIterpreterServiceUnavailableException;

// TODO: delete
public interface IMIDIterpreterProxy {
    String getSiretByMID(String MID) throws MIDIterpreterServiceUnavailableException, BadMIDException;
}
