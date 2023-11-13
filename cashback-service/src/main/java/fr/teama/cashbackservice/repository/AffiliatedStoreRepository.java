package fr.teama.cashbackservice.repository;

import fr.teama.cashbackservice.models.AffiliatedStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliatedStoreRepository extends JpaRepository<AffiliatedStore, Long> {
    AffiliatedStore findByName(String name);
    AffiliatedStore findBySiret(String siret);
}
