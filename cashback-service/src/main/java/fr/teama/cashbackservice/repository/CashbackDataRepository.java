package fr.teama.cashbackservice.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CashbackDataRepository extends JpaRepository<RocketData, Long> {
}
