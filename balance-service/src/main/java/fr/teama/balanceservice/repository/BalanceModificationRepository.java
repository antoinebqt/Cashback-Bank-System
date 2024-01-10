package fr.teama.balanceservice.repository;

import fr.teama.balanceservice.models.BalanceModification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceModificationRepository extends JpaRepository<BalanceModification, Long> {
}
