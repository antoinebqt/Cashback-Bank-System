package fr.teama.bankservice.repository;

import fr.teama.bankservice.models.BankAccount;
import fr.teama.bankservice.models.BankUser;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BankDataRepository extends JpaRepository<BankUser, Long> {
}
