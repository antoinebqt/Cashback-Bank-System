package fr.teama.transactionservice.repository.account;

import fr.teama.transactionservice.models.account.BankAccount;
import fr.teama.transactionservice.models.account.BankAccountCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<BankAccountCard, Long> {
    BankAccountCard findByCardNumber(String cardNumber);
}
