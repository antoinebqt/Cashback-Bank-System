package fr.teama.transactionservice.repository;

import fr.teama.transactionservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//    @Query(value = "SELECT t FROM Transaction t WHERE t.createdAt >= CURRENT_TIMESTAMP - INTERVAL 2 HOUR", nativeQuery = true)
//    List<Transaction> findTransactionsInLast2Hours();
}
