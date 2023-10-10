package fr.teama.cashbackservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashbackDataRepository extends JpaRepository<String, Long> {
}
