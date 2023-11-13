package fr.teama.externaldecathlonmockservice.controllers;

import fr.teama.externaldecathlonmockservice.helpers.LoggerHelper;
import fr.teama.externaldecathlonmockservice.interfaces.PurchaseReturnedRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Controller
@CrossOrigin
@RequestMapping(path = PurchaseReturnedController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class PurchaseReturnedController {
    public static final String BASE_URI = "/api/store";

    @Autowired
    PurchaseReturnedRetriever purchaseReturnedRetriever;

    @GetMapping(path = "/purchaseReturned")
    public ResponseEntity<List<Long>> getPurchasedReturned(@RequestBody List<Long> purchasedItems) {
        LoggerHelper.logInfo("Decathlon: Request received to get the list of purchase returned");
        return ResponseEntity.ok(purchaseReturnedRetriever.getPurchasedReturned(purchasedItems));
    }

    @PostMapping(path = "/purchaseMade")
    public ResponseEntity<String> makePurchase(@RequestBody Long id) {
        LoggerHelper.logInfo("Request received to get make purchase in Store Decathlon");
        purchaseReturnedRetriever.makePurchase(id);
        return ResponseEntity.ok("Purchase made in Store Carrefour");
    }

    @PostMapping(path = "/return")
    public ResponseEntity<String> returnPurchase(@RequestBody Long id) {
        LoggerHelper.logInfo("Request received to get return purchase in Store Decathlon");
        purchaseReturnedRetriever.returnPurchase(id);
        return ResponseEntity.ok("Purchase returned in Store Decathlon");
    }
}

