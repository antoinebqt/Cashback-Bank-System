package fr.teama.cashbackservice.controllers;

import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.IStatManager;
import fr.teama.cashbackservice.models.AdminCashbackStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@CrossOrigin
@RequestMapping(path = StatController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class StatController {
    public static final String BASE_URI = "/api/stat";

    @Autowired
    IStatManager statManager;


    @GetMapping("/cashback")
    public ResponseEntity<AdminCashbackStatistic> getCashbackStatistic() {
        LoggerHelper.logInfo("Request received to get cashback statistics");
        return ResponseEntity.ok(statManager.getCashback());
    }
}
