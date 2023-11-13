package fr.teama.cashbackservice.interfaces;

import fr.teama.cashbackservice.connectors.externalDTO.TransactionDTO;
import fr.teama.cashbackservice.models.AffiliatedStore;
import org.h2.mvstore.tx.Transaction;

import java.util.List;

public interface CashbackCancel {

    List<TransactionDTO> getTransactionsToCancel(AffiliatedStore affiliatedStore);
}
