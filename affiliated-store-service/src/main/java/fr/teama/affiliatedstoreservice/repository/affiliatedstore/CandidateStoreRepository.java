package fr.teama.affiliatedstoreservice.repository.affiliatedstore;

import fr.teama.affiliatedstoreservice.models.affiliatedstore.CandidateStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateStoreRepository extends JpaRepository<CandidateStore, Long>{
    List<CandidateStore> findAllByAcceptedIsFalseAndRefusedIsFalse();
}
