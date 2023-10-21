package fr.teama.bankservice.controllers;

import fr.teama.bankservice.controllers.dto.BankTransferDTO;
import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.interfaces.BalanceModifier;
import org.springframework.beans.factory.annotation.Autowired;
import fr.teama.bankservice.controllers.DTO.BankTransferDTO;
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

}
