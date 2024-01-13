package fr.teama.affiliatedstoreservice.controllers;

import fr.teama.affiliatedstoreservice.helpers.LoggerHelper;
import fr.teama.affiliatedstoreservice.interfaces.IAffiliatedStoreCatalog;
import fr.teama.affiliatedstoreservice.models.affiliatedstore.AffiliatedStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@CrossOrigin
@RequestMapping(path = StoreCatalogController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class StoreCatalogController {
    public static final String BASE_URI = "/api/catalog";

    @Autowired
    IAffiliatedStoreCatalog affiliatedStoreCatalog;

    @PostMapping(path = "/id")
    public ResponseEntity<AffiliatedStore> getAffiliatedStoreById(@RequestBody Long id) {
        LoggerHelper.logInfo("Request received to get affiliated store by id : " + id);
        return ResponseEntity.ok(affiliatedStoreCatalog.getAffiliatedStoreById(id));
    }

    @PostMapping(path = "/name")
    public ResponseEntity<AffiliatedStore> getAffiliatedStoreByName(@RequestBody String name) {
        LoggerHelper.logInfo("Request received to get affiliated store by name : " + name);
        return ResponseEntity.ok(affiliatedStoreCatalog.getAffiliatedStoreByName(name));
    }

    @GetMapping(path = "/siret/{siret}")
    public ResponseEntity<AffiliatedStore> getAffiliatedStoreBySiret(@PathVariable("siret") String siret) {
        LoggerHelper.logInfo("Request received to get affiliated store with siret : " + siret);
        AffiliatedStore affiliatedStore = affiliatedStoreCatalog.getAffiliatedStoreBySiret(siret);
        if (affiliatedStore == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(affiliatedStore);
    }

    @GetMapping
    public ResponseEntity<List<AffiliatedStore>> getAllAffiliatedStores() {
        LoggerHelper.logInfo("Request received to get all affiliated stores");
        return ResponseEntity.ok(affiliatedStoreCatalog.getAllAffiliatedStores());
    }

    @GetMapping(path = "/{siret}/cashback-rate")
    public ResponseEntity<Float> getCashbackRateFromSiret(@PathVariable("siret") String siret) {
        LoggerHelper.logInfo("Request received to get cashback rate of affiliated store with siret : " + siret);
        AffiliatedStore affiliatedStore = affiliatedStoreCatalog.getAffiliatedStoreBySiret(siret);
        if (affiliatedStore == null) {
            LoggerHelper.logInfo("This SIRET is not an affiliated store");
            return ResponseEntity.status(404).body(null);
        }
        LoggerHelper.logInfo("Found cashback rate of " + affiliatedStore.getCashbackRate() + "% for this SIRET");
        return ResponseEntity.ok(affiliatedStore.getCashbackRate());
    }

}
