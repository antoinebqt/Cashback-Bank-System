package fr.teama.bankservice.interfaces.proxy;

import fr.teama.bankservice.exceptions.BadMIDException;
import fr.teama.bankservice.exceptions.MIDIterpreterServiceUnavailableException;

public interface IMIDIterpreterProxy {
    String getSiretByMID(String MID) throws MIDIterpreterServiceUnavailableException, BadMIDException;
}
