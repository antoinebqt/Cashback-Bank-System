package fr.teama.bankservice.controllers;

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
@RequestMapping(path = BankController.BASE_URI, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class BankController {
    public static final String BASE_URI = "/api/bank";

    @Autowired
    UserRegistration userRegistration;

    @Autowired
    BankUserRepository bankUserRepository;

    @GetMapping
    public String getBank() {
        return "Bank";
    }

    @PostMapping("/register")
    public ResponseEntity<BankUser> register(@RequestBody String firstName, String lastName, String email, String password) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRegistration.registerUser(firstName, lastName, email, password));
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<BankUser>> getAllUser() {
        System.out.println("Request received for getting all users");
        List<BankUser> userList = bankUserRepository.findAll();
        return ResponseEntity.ok(userList);
    }

}
