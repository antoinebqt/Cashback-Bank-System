package fr.teama.bankservice.repository;

import fr.teama.bankservice.models.BankAccount;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BankDataRepository extends JpaRepository<BankAccount, Long> {
}
