package fr.teama.accountservice.repository;

import fr.teama.accountservice.models.BalanceModification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceModificationRepository extends JpaRepository<BalanceModification, Long> {
}
