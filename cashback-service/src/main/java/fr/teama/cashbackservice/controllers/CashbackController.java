package fr.teama.cashbackservice.controllers;

import fr.teama.cashbackservice.controllers.dto.CashbackDTO;
import fr.teama.cashbackservice.helpers.LoggerHelper;
import fr.teama.cashbackservice.models.Cashback;
import fr.teama.cashbackservice.repository.CashbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@CrossOrigin
@RequestMapping(path = CashbackController.BASE_URI, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CashbackController {

    public static final String BASE_URI = "/api/cashback";

    private final CashbackRepository cashbackRepository;

    @Autowired
    CashbackController(CashbackRepository cashbackRepository) {
        this.cashbackRepository = cashbackRepository;
    }

    @GetMapping("/last-month")
    public ResponseEntity<List<CashbackDTO>> getCashbackTransactionsLastMonth() {
        LoggerHelper.logInfo("Request received to get the cashback of the last month");
        List<Cashback> cashbackLastMonth = cashbackRepository.findAllWithTimestampOlderThan(LocalDateTime.now().minusMonths(1));
        List<CashbackDTO> cashbackDTOs = new ArrayList<>();
        for (Cashback cashback : cashbackLastMonth) {
            cashbackDTOs.add(new CashbackDTO(cashback));
        }
        return ResponseEntity.ok(cashbackDTOs);
    }

    @GetMapping("/transaction-id-last-month-with-siret")
    public ResponseEntity<List<CashbackDTO>> getCashbackTransactionIdsLastMonthWithSiret(@RequestBody String siret) {
        LoggerHelper.logInfo("Request received to get the cashback of the last month for siret " + siret);
        List<Cashback> cashbackLastMonth = cashbackRepository.findAllWithTimestampOlderThanAndSiret(LocalDateTime.now().minusMonths(1), siret);
        List<CashbackDTO> cashbackDTOs = new ArrayList<>();
        for (Cashback cashback : cashbackLastMonth) {
            cashbackDTOs.add(new CashbackDTO(cashback));
        }
        return ResponseEntity.ok(cashbackDTOs);
    }

    @GetMapping
    public ResponseEntity<List<CashbackDTO>> getCashbackTransactions() {
        LoggerHelper.logInfo("Request received to get the cashback");
        List<Cashback> cashback = cashbackRepository.findAll();
        List<CashbackDTO> cashbackDTOs = new ArrayList<>();
        for (Cashback cashbackToConvert : cashback) {
            cashbackDTOs.add(new CashbackDTO(cashbackToConvert));
        }
        return ResponseEntity.ok(cashbackDTOs);
    }
}
