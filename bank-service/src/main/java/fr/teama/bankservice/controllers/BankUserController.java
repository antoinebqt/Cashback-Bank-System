package fr.teama.bankservice.controllers;

import fr.teama.bankservice.controllers.dto.BankUserConnectionDTO;
import fr.teama.bankservice.controllers.dto.BankUserDTO;
import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.exceptions.BankUserWithEmailAlreadyExistException;
import fr.teama.bankservice.exceptions.InvalidAccountPasswordException;
import fr.teama.bankservice.helpers.LoggerHelper;
import fr.teama.bankservice.interfaces.BankUserInformation;
import fr.teama.bankservice.interfaces.UserRegistration;
import fr.teama.bankservice.models.BankUser;
import fr.teama.bankservice.repository.BankUserRepository;
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
    public static final String BASE_URI = "/api/bank";

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
}
