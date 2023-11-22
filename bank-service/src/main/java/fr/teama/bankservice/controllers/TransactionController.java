package fr.teama.bankservice.controllers;

import fr.teama.bankservice.components.TransactionHandler;
import fr.teama.bankservice.controllers.dto.BankUserConnectionDTO;
import fr.teama.bankservice.controllers.dto.PaymentDTO;
import fr.teama.bankservice.exceptions.*;
import fr.teama.bankservice.controllers.dto.TransactionDTO;
import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.exceptions.InvalidAccountPasswordException;
import fr.teama.bankservice.exceptions.InvalidCardException;
import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.BankUserInformation;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Payment;
import fr.teama.bankservice.models.Transaction;
import fr.teama.bankservice.repository.CardRepository;
import fr.teama.bankservice.services.RabbitMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BankUserInformation bankUserInformation;
    @Autowired
    private RabbitMQProducerService rabbitMQProducerService;

    @PostMapping("/cashback-message")
    public ResponseEntity<String> sendCashbackMessage(@RequestBody String message) {
        LoggerHelper.logInfo("Request received to send cashback message: " + message);
        rabbitMQProducerService.sendCashbackMessage(message);
        return ResponseEntity.ok("Message sent");
    }

    @PostMapping("/cashback-message-test")
    public ResponseEntity<String> sendCashbackMessage2(@RequestBody String message) {
        LoggerHelper.logInfo("Request received to send payment message: " + message);
        Payment payment = new Payment(1234, message, "randomID");
        rabbitMQProducerService.sendCashbackMessage(payment);
        return ResponseEntity.ok("Message sent");
    }

    @PostMapping("/pay")
    public ResponseEntity<Transaction> pay(@RequestBody PaymentDTO paymentDTO) throws InvalidCardException, PaymentFailedException {
        LoggerHelper.logInfo("Request received to pay " + paymentDTO);
        Card card=cardRepository.findByCardNumber(paymentDTO.getCardNumber());
        if (card!=null && card.getCvv().equals(paymentDTO.getCvv()) && card.getExpirationDate().equals(paymentDTO.getExpirationDate())) {
            return ResponseEntity.ok(transactionHandler.pay(card, paymentDTO.getMid(), paymentDTO.getAmount()));
        }
        else{
            throw new InvalidCardException();
        }
    }

    @GetMapping
    public List<Transaction> getTransaction() {
        LoggerHelper.logInfo("Request received to get all transactions");
        return transactionHandler.getTransactions();
    }

    @PostMapping("/cashback-by-store")
    public List<TransactionDTO> cashbackTransactionByStore(String siret) {
        LoggerHelper.logInfo("Request received to get all cashback transactions");
        return transactionHandler.getCashbackTransactionsByStore(siret);
    }


    @GetMapping("/cashback")
    public List<TransactionDTO> getCashbackTransaction() {
        LoggerHelper.logInfo("Request received to get all cashback transactions");
        return transactionHandler.getCashbackTransactions();
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

    @PostMapping("/cashback-cancellation/{transactionId}")
    public void cancelCashback(@PathVariable Long transactionId) {
        LoggerHelper.logInfo("Request received to cancel cashback of transaction " + transactionId);
        transactionHandler.cancelCashback(transactionId);
    }
}
