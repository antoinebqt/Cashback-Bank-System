package fr.teama.balanceservice.repository;

import fr.teama.balanceservice.models.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long> {
    BankUser findByEmail(String email);

}
