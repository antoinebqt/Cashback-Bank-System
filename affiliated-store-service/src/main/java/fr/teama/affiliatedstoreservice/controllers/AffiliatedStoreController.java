package fr.teama.affiliatedstoreservice.controllers;

import fr.teama.affiliatedstoreservice.exceptions.AffiliatedStoreAlreadyExist;
import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.IAffiliatedStoreManager;
import fr.teama.affiliatedstoreservice.models.AffiliatedStore;
import fr.teama.affiliatedstoreservice.models.CandidateStore;
import fr.teama.affiliatedstoreservice.repository.CandidateStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@CrossOrigin
@RequestMapping(path = AffiliatedStoreController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class AffiliatedStoreController {
    public static final String BASE_URI = "/api/store";



    @Autowired
    IAffiliatedStoreManager affiliatedStoreManager;

    @Autowired
    CandidateStoreRepository candidateStoreRepository;
    // rest methode to create a new store, and find new store by id, name, and all stores
    @PostMapping(path = "/create")
    public ResponseEntity<String> createAffiliatedStore(@RequestBody AffiliatedStore affiliatedStore) {
        try {
            LoggerHelper.logInfo("Request received to create new affiliated store : " + affiliatedStore.toString());
            return ResponseEntity.ok(affiliatedStoreManager.createAffiliatedStore(affiliatedStore).toString());
        } catch (AffiliatedStoreAlreadyExist e) {
            return ResponseEntity.status(401).body("Affiliated store with name '" + affiliatedStore.getName() + "' already exists.");
        }
    }
    @PostMapping(path = "/apply")
    public ResponseEntity<String> applyCandidateStore(@RequestBody CandidateStore candidateStore) {
        try {
            LoggerHelper.logInfo("Request received to apply new candidate store : " + candidateStore.toString());
            return ResponseEntity.ok(candidateStoreRepository.save(candidateStore).toString());
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Candidate store with name '" + candidateStore.getName() + "' already exists.");
        }
    }
    @GetMapping(path="/all-candidate")
    public ResponseEntity<Iterable<CandidateStore>> getAllCandidateStore(){
        LoggerHelper.logInfo("Request received to get all candidate store");
        return ResponseEntity.ok(candidateStoreRepository.findAllByAcceptedIsFalseAndRefusedIsFalse());
    }


}
