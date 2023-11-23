package fr.teama.cashbackservice.controllers;

import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.interfaces.ICashbackManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@CrossOrigin
@RequestMapping(path = CashbackController.BASE_URI, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CashbackController {

    public static final String BASE_URI = "/api/cashback";

    private final ICashbackManager cashbackManager;

    @Autowired
    CashbackController(ICashbackManager cashbackManager) {
        this.cashbackManager = cashbackManager;
    }

    @PostMapping(path= "cancelled-cashback-transactions")
    public ResponseEntity<String> manuallyCheckCancelledCashbackTransactions() {
        LoggerHelper.logInfo("Request received to manually check cancelled cashback transactions for affiliated stores");

        //affiliatedStoreManager.manuallyCheckCancelledCashbackTransactions();

        return ResponseEntity.ok("Cashback removed of bank accounts with cancelled cashback transactions.");
    }
}
