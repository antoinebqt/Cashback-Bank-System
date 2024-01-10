package fr.teama.balanceservice.interfaces;

import fr.teama.balanceservice.exceptions.BankAccountNotFoundException;
import fr.teama.balanceservice.externalDTO.BalanceMessageDTO;
import fr.teama.balanceservice.models.BalanceModification;

public interface BalanceModifier {

    void addBalance(String iban, Double amount) throws BankAccountNotFoundException;

    void changeBalance(BalanceMessageDTO balanceMessageDTO) throws BankAccountNotFoundException;
}
