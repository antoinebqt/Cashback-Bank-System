package fr.teama.bankservice.interfaces.proxy;

import fr.teama.bankservice.models.Transaction;

public interface ICashbackProxy {

    Transaction updateWithPotentialCashback(Transaction transaction);
}
