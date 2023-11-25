package fr.teama.affiliatedstoreservice.controllers;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.ICancelledTransactionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@CrossOrigin
@RequestMapping(path = CancelledTransactionController.BASE_URI)
public class CancelledTransactionController {
    public static final String BASE_URI = "/api/cancelled-transaction";

    @Autowired
    ICancelledTransactionAdapter cancelledTransactionAdapter;

    @PostMapping
    public ResponseEntity<String> manuallyCheckCancelledTransaction() {
        LoggerHelper.logInfo("Manually check cancelled transaction");
        cancelledTransactionAdapter.checkCancelledTransaction();
        return ResponseEntity.ok("Manually check cancelled transaction");
    }

}
