package fr.teama.cashbackservice.interfaces;

import fr.teama.cashbackservice.connectors.externalDTO.TransactionDTO;
import fr.teama.cashbackservice.models.AffiliatedStore;

import java.util.List;

public interface CashbackCancelAdaptor {

    List<TransactionDTO> getTransactionsToCancel(AffiliatedStore affiliatedStore);
}
