package fr.teama.affiliatedstoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AffiliatedStoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AffiliatedStoreServiceApplication.class, args);
    }

}
