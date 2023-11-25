package fr.teama.cashbackservice.repository;

import fr.teama.cashbackservice.models.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Long> {

    @Query("SELECT cb FROM Cashback cb WHERE cb.timestamp <= :date")
    List<Cashback> findAllWithTimestampOlderThan(@Param("date") LocalDateTime date);
}
