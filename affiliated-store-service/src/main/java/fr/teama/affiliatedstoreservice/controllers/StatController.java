package fr.teama.affiliatedstoreservice.controllers;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.IStatManager;
import fr.teama.affiliatedstoreservice.models.AdminCashbackStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return ResponseEntity.ok(statManager.getCashbackStat());
    }
}
