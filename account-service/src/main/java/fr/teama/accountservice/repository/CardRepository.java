package fr.teama.accountservice.repository;

import fr.teama.accountservice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String cardNumber);
}
