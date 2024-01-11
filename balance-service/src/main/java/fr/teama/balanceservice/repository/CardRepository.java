package fr.teama.balanceservice.repository;

import fr.teama.balanceservice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String cardNumber);
}
