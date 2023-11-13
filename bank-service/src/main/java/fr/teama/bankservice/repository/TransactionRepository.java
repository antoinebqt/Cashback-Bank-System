package fr.teama.bankservice.repository;

import fr.teama.bankservice.models.Card;
import fr.teama.bankservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCashbackReturnedIsNot(double cashbackReturned);

}
