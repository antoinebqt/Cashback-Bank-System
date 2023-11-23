package fr.teama.cashbackservice.repository;

import fr.teama.cashbackservice.models.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Long> {

}
