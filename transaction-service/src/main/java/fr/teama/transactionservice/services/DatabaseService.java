package fr.teama.transactionservice.services;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseService {

    @Autowired
    @Qualifier("transactionEntityManagerFactory")
    private EntityManagerFactory transactionEntityManagerFactory;

    @Autowired
    @Qualifier("accountEntityManagerFactory")
    private EntityManagerFactory accountEntityManagerFactory;

    @Transactional("transactionTransactionManager")
    public void performOperationOnPrimaryDatabase() {
        // Opérations sur la base de données principale
    }

    @Transactional("accountTransactionManager")
    public void performOperationOnSecondaryDatabase() {
        // Opérations sur la base de données secondaire
    }
}
