package fr.teama.transactionservice.repository.transaction;

import fr.teama.transactionservice.models.transaction.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("transactionEntityManagerFactory")
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
