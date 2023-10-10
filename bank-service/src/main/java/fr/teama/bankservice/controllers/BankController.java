package fr.teama.bankservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
