package fr.teama.affiliatedstoreservice.interfaces.proxy;

import fr.teama.affiliatedstoreservice.models.CashbackDTO;

import java.util.List;

public interface ICashbackProxy {
    List<CashbackDTO> getCashbackTransactionsLastMonth();

    List<CashbackDTO> getCashbackTransactions();

    List<String> getCashbackTransactionIdsLastMonthWithSiret(String siret);
}
