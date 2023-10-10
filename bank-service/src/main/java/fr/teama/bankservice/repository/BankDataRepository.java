package fr.teama.bankservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BankDataRepository extends JpaRepository<String, Long> {
}
