package fr.teama.accountservice.controllers;

import fr.teama.accountservice.controllers.dto.BankTransferDTO;
import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.helpers.LoggerHelper;
import fr.teama.accountservice.interfaces.BalanceChecker;
import fr.teama.accountservice.interfaces.BalanceModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = BalanceController.BASE_URI, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class BalanceController {
    public static final String BASE_URI = "/api/balance";

    @Autowired
    BalanceModifier balanceModifier;

    @Autowired
    BalanceChecker balanceChecker;

    @PostMapping("/add")
    public ResponseEntity<String> addBalance(@RequestBody BankTransferDTO bankTransferDTO) throws BankAccountNotFoundException {
        LoggerHelper.logInfo("Received bank transfer request: " + bankTransferDTO.toString());
        balanceModifier.addBalance(bankTransferDTO.getIban(), bankTransferDTO.getAmount());
        return ResponseEntity.ok("Balance added");
    }

    @PostMapping("/check/{bankAccountId}")
    public ResponseEntity<Boolean> checkBalance(@PathVariable Long bankAccountId, @RequestBody double amount) throws BankAccountNotFoundException {
        LoggerHelper.logInfo("Received balance check request for bank account id " + bankAccountId + " with amount " + amount);
        return ResponseEntity.ok(balanceChecker.checkBalance(bankAccountId, amount));
    }
}
