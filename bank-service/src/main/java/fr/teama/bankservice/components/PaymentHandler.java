package fr.teama.bankservice.components;

import fr.teama.bankservice.connectors.externalDTO.MastercardPaymentDTO;
import fr.teama.bankservice.exceptions.*;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.IPayment;
import fr.teama.bankservice.interfaces.proxy.IMIDIterpreterProxy;
import fr.teama.bankservice.interfaces.proxy.IMastercardProxy;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentHandler implements IPayment {
    @Autowired
    private BalanceManager balanceManager;
    @Autowired
    private IMIDIterpreterProxy midIterpreterProxy;
    @Autowired
    private IMastercardProxy mastercardProxy;

    @Override
    public Payment pay(Card card, String MID, double amount) throws PaymentFailedException {
        try {
            String siret = midIterpreterProxy.getSiretByMID(MID);
            MastercardPaymentDTO mastercardPaymentDTO = new MastercardPaymentDTO(MID, amount);
            String mastercardTransactionId = mastercardProxy.transferPayment(mastercardPaymentDTO);
            balanceManager.debitBalance(card.getBankAccount(), amount);
            LoggerHelper.logInfo("Payment of " + amount + " transferred for " + siret);
            return new Payment(amount, siret, mastercardTransactionId);
        } catch (Exception e) {
            LoggerHelper.logError("Payment failed");
            throw new PaymentFailedException();
        }




    }
}
