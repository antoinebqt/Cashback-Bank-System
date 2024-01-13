package fr.teama.affiliatedstoreservice.repository;

import fr.teama.affiliatedstoreservice.models.CandidateStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateStoreRepository extends JpaRepository<CandidateStore, Long>{
    List<CandidateStore> findAllByAcceptedIsFalseAndRefusedIsFalse();
}
