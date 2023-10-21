package fr.teama.bankservice.controllers;

import fr.teama.bankservice.components.TransactionHandler;
import fr.teama.bankservice.controllers.DTO.PaymentDTO;
import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = BalanceController.BASE_URI, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class TransactionController {
    public static final String BASE_URI = "/api/transaction";
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionHandler transactionHandler;

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("PaymentDTO: " + paymentDTO.toString());
        Card card=cardRepository.findByCardNumber(paymentDTO.getCardNumber());
        if (card!=null && card.getCvv().equals(paymentDTO.getCvv()) && card.getExpirationDate().equals(paymentDTO.getExpirationDate())) {
            try {
                return ResponseEntity.ok(transactionHandler.pay(card, paymentDTO.getBeneficiary(), paymentDTO.getAmount()).toString());
            } catch (NotEnoughMoneyException e) {
                return ResponseEntity.badRequest().body("Not enough money");
            }
        }
        else{
            return ResponseEntity.badRequest().body("Invalid card");
        }
    }
}
