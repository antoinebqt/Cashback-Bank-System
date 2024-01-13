package fr.teama.balanceservice.repository;

import fr.teama.balanceservice.models.BalanceModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceModificationRepository extends JpaRepository<BalanceModification, Long> {
    Optional<BalanceModification> findByTransactionIdAndCashbackIsFalse(Long transactionId);
    Optional<BalanceModification> findByTransactionIdAndCashbackIsTrue(Long transactionId);

}
