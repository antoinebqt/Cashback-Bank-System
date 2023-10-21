package fr.teama.bankservice.controllers;

import fr.teama.bankservice.controllers.dto.BankUserDTO;
import fr.teama.bankservice.interfaces.UserRegistration;
import fr.teama.bankservice.models.BankAccount;
import fr.teama.bankservice.models.BankUser;
import fr.teama.bankservice.repository.BankAccountRepository;
import fr.teama.bankservice.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = BankUserController.BASE_URI, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class BankUserController {
    public static final String BASE_URI = "/api/bank";

    @Autowired
    UserRegistration userRegistration;

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @GetMapping
    public String getBank() {
        return "Bank";
    }

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<BankUser> register(@RequestBody BankUserDTO bankUserDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRegistration.registerUser(bankUserDTO.getFirstName(), bankUserDTO.getLastName(), bankUserDTO.getEmail(), bankUserDTO.getPassword()));
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<BankUser>> getAllUser() {
        System.out.println("Request received for getting all users");
        List<BankUser> userList = bankUserRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/all-bank-accounts")
    public ResponseEntity<List<BankAccount>> getAllBankAccount() {
        System.out.println("Request received for getting all bank accounts");
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        System.out.println(bankAccountList.get(0).getBankUser().getEmail());
        return ResponseEntity.ok(bankAccountList);
    }
}
