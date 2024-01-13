package fr.teama.transactionservice.repository.account;

import fr.teama.transactionservice.models.account.BankAccount;
import fr.teama.transactionservice.models.account.BankAccountCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("accountEntityManagerFactory")
public interface AccountRepository extends JpaRepository<BankAccount, Long> {
}
