package fr.teama.bankservice.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface BankDataRepository extends JpaRepository<RocketData, Long> {
}
