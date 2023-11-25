package fr.teama.cashbackservice.interfaces.proxy;


import fr.teama.cashbackservice.exceptions.BadMIDException;
import fr.teama.cashbackservice.exceptions.MIDIterpreterServiceUnavailableException;

public interface IMIDIterpreterProxy {
    String getSiretByMID(String MID) throws MIDIterpreterServiceUnavailableException, BadMIDException;
}
