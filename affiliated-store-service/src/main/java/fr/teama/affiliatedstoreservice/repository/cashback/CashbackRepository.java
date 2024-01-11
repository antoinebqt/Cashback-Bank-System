package fr.teama.affiliatedstoreservice.repository.cashback;

import fr.teama.affiliatedstoreservice.models.cashback.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Integer> {
    @Query("SELECT cb FROM Cashback cb WHERE cb.timestamp >= :date")
    List<Cashback> findAllWithTimestampGreaterThan(@Param("date") LocalDateTime date);

    // Find all with timestamp older than date and siret
    @Query("SELECT cb FROM Cashback cb WHERE cb.timestamp >= :date AND cb.siret = :siret")
    List<Cashback> findAllWithTimestampGreaterThanAndSiret(@Param("date") LocalDateTime date, @Param("siret") String siret);

    Cashback findCashbackByTransactionId(Long transactionId);
}
