package fr.teama.bankservice.controllers;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = BankController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class BankController {
    public static final String BASE_URI = "/api/bank";

    @GetMapping
    public String getBank() {
        return "Bank";
    }

    @PostMapping("/register")
    public String register() {
        return "Account created";
    }

}
