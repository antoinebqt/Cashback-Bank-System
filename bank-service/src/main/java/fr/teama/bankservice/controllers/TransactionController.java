package fr.teama.bankservice.controllers;

import fr.teama.bankservice.components.TransactionHandler;
import fr.teama.bankservice.controllers.dto.BankUserConnectionDTO;
import fr.teama.bankservice.controllers.dto.PaymentDTO;
import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.exceptions.InvalidAccountPasswordException;
import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.BankUserInformation;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Transaction;
import fr.teama.bankservice.repository.CardRepository;
import fr.teama.bankservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = TransactionController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class TransactionController {
    public static final String BASE_URI = "/api/transaction";
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionHandler transactionHandler;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BankUserInformation bankUserInformation;

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody PaymentDTO paymentDTO) {
        LoggerHelper.logInfo("PaymentDTO: " + paymentDTO.toString());
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

    @GetMapping
    public List<Transaction> getTransaction() {
        LoggerHelper.logInfo("Request received to get all transactions");
        return transactionHandler.getTransactions();
    }

    @PostMapping("/user")
    public List<Transaction> getBankUserTransaction(@RequestBody BankUserConnectionDTO bankUserConnectionDTO) throws BankAccountNotFoundException, InvalidAccountPasswordException {
        LoggerHelper.logInfo("Request received to get all transactions of user " + bankUserConnectionDTO.getEmail());
        return bankUserInformation.getTransactions(bankUserConnectionDTO.getEmail(), bankUserConnectionDTO.getPassword());
    }

    @PostMapping("/user-cashback")
    public Double getBankUserTransactionsTotalCashback(@RequestBody BankUserConnectionDTO bankUserConnectionDTO) throws BankAccountNotFoundException, InvalidAccountPasswordException {
        LoggerHelper.logInfo("Request received to get total earned cashback of user " + bankUserConnectionDTO.getEmail());
        return bankUserInformation.getAmountEarnedWithTransactionCashback(bankUserConnectionDTO.getEmail(), bankUserConnectionDTO.getPassword());
    }
}
