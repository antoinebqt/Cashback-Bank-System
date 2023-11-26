package fr.teama.accountservice.controllers;

import fr.teama.accountservice.controllers.dto.BankUserConnectionDTO;
import fr.teama.accountservice.controllers.dto.BankUserDTO;
import fr.teama.accountservice.controllers.dto.CardDTO;
import fr.teama.accountservice.exceptions.BankAccountNotFoundException;
import fr.teama.accountservice.exceptions.BankUserWithEmailAlreadyExistException;
import fr.teama.accountservice.exceptions.InvalidAccountPasswordException;
import fr.teama.accountservice.exceptions.InvalidCardException;
import fr.teama.accountservice.helpers.LoggerHelper;
import fr.teama.accountservice.interfaces.BankUserInformation;
import fr.teama.accountservice.interfaces.UserRegistration;
import fr.teama.accountservice.models.BankAccount;
import fr.teama.accountservice.models.BankUser;
import fr.teama.accountservice.models.Card;
import fr.teama.accountservice.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = BankUserController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class BankUserController {
    public static final String BASE_URI = "/api/account";

    @Autowired
    UserRegistration userRegistration;

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankUserInformation bankUserInformation;

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<BankUser> register(@RequestBody BankUserDTO bankUserDTO) throws BankUserWithEmailAlreadyExistException {
        LoggerHelper.logInfo("Request received for registering user " + bankUserDTO.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRegistration.registerUser(bankUserDTO.getFirstName(), bankUserDTO.getLastName(), bankUserDTO.getEmail(), bankUserDTO.getPassword()));
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<BankUser>> getAllUser() {
        LoggerHelper.logInfo("Request received for getting all users");
        List<BankUser> userList = bankUserRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/user")
    public ResponseEntity<BankUser> getUser(@RequestBody BankUserConnectionDTO bankUserConnectionDTO) throws BankAccountNotFoundException, InvalidAccountPasswordException {
        LoggerHelper.logInfo("Request received for getting user " + bankUserConnectionDTO.getEmail());
        BankUser user = bankUserInformation.getBankUser(bankUserConnectionDTO.getEmail(), bankUserConnectionDTO.getPassword());
        return ResponseEntity.ok(user);
    }
    @PostMapping("/account-by-card")
    public ResponseEntity<Long> getBankAccountIdByCard(@RequestBody CardDTO card) throws BankAccountNotFoundException, InvalidCardException {
        LoggerHelper.logInfo("Request received for getting user: " + card);
        BankAccount account = bankUserInformation.getBankAccountByCard(card);
        return ResponseEntity.ok(account.getId());
    }
}
