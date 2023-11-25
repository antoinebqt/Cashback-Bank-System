package fr.teama.accountservice.repository;

import fr.teama.accountservice.models.BankUser;
import fr.teama.accountservice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long> {
    BankUser findByEmail(String email);

}
