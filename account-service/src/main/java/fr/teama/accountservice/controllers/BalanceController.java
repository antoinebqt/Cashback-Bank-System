package fr.teama.accountservice.controllers;

import fr.teama.accountservice.controllers.dto.BankTransferDTO;
import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.helpers.LoggerHelper;
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

    @PostMapping("/add")
    public ResponseEntity<String> addBalance(@RequestBody BankTransferDTO bankTransferDTO) throws BankAccountNotFoundException {
        System.out.println("Received bank transfer request: " + bankTransferDTO.toString());
        balanceModifier.addBalance(bankTransferDTO.getIban(), bankTransferDTO.getAmount());
        return ResponseEntity.ok("Balance added");
    }

    @PostMapping(path = "/add-cashback/{bankAccountId}")
    public ResponseEntity<String> addCashbackToAccount(@PathVariable Long bankAccountId, @RequestBody Double cashbackAmount) {
        try {
            LoggerHelper.logInfo("Received request to add " + cashbackAmount + "€ to bank account " + bankAccountId);

            balanceModifier.addCashback(bankAccountId, cashbackAmount);

            LoggerHelper.logInfo("Cashback added successfully");
            return ResponseEntity.ok("Cashback added successfully");
        } catch (Exception e) {
            LoggerHelper.logError("Failed to add cashback to bank account " + bankAccountId);
            LoggerHelper.logError(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(path = "/remove-cashback/{bankAccountId}")
    public ResponseEntity<String> removeCashbackToAccount(@PathVariable Long bankAccountId, @RequestBody Double cashbackToRemove) {
        try {
            LoggerHelper.logInfo("Received request to remove " + cashbackToRemove + "€ to bank account " + bankAccountId);

            balanceModifier.removeCashback(bankAccountId, cashbackToRemove);

            LoggerHelper.logInfo("Cashback removed successfully");
            return ResponseEntity.ok("Cashback removed successfully");
        } catch (Exception e) {
            LoggerHelper.logError("Failed to removed cashback to bank account " + bankAccountId);
            LoggerHelper.logError(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
