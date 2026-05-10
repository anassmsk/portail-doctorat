package com.doctorat.service_inscriptions.repository;

import com.doctorat.service_inscriptions.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByDoctorantId(Long doctorantId);
    List<Inscription> findByStatut(Inscription.StatutInscription statut);
    List<Inscription> findByDirecteurEmail(String directeurEmail);
}