package fr.teama.bankservice.controllers;

import fr.teama.bankservice.interfaces.UserRegistration;
import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.BankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = BankController.BASE_URI, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class BankController {
    public static final String BASE_URI = "/api/bank";

    @Autowired
    UserRegistration userRegistration;

    @GetMapping
    public String getBank() {
        return "Bank";
    }

    @PostMapping("/register")
    public ResponseEntity<Card> register(@RequestBody BankUser user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRegistration.registerUser(user));
    }

}
