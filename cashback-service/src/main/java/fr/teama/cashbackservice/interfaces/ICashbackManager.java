package fr.teama.cashbackservice.interfaces;

import fr.teama.cashbackservice.controllers.dto.PaymentDTO;

public interface ICashbackManager {
    Double addPotentialCashback(PaymentDTO payment, Long bankAccountId);
}
