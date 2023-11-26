package fr.teama.accountservice.repository;

import fr.teama.accountservice.models.BalanceMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceMessageRepository extends JpaRepository<BalanceMessage, Long> {
}
