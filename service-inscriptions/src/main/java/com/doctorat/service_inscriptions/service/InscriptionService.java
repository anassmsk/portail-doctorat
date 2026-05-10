package com.doctorat.service_inscriptions.service;

import com.doctorat.service_inscriptions.dto.InscriptionRequest;
import com.doctorat.service_inscriptions.entity.Inscription;
import com.doctorat.service_inscriptions.repository.InscriptionRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class InscriptionService {

    private final InscriptionRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InscriptionService(InscriptionRepository repository,
                              KafkaTemplate<String, Object> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Inscription soumettre(InscriptionRequest request) {
        Inscription inscription = new Inscription();
        inscription.setDoctorantId(request.getDoctorantId());
        inscription.setNomDoctorant(request.getNomDoctorant());
        inscription.setEmailDoctorant(request.getEmailDoctorant());
        inscription.setSujetThese(request.getSujetThese());
        inscription.setDirecteurEmail(request.getDirecteurEmail());
        inscription.setCollaboration(request.getCollaboration());
        inscription.setAnnee(LocalDate.now().getYear());
        inscription.setStatut(Inscription.StatutInscription.EN_ATTENTE);
        Inscription saved = repository.save(inscription);
        kafkaTemplate.send("inscription-soumise", saved.getId().toString(), saved);
        return saved;
    }

    public Inscription validerDirecteur(Long id, String commentaire) {
        Inscription inscription = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvee"));
        inscription.setStatut(Inscription.StatutInscription.VALIDEE_DIRECTEUR);
        inscription.setCommentaireDirecteur(commentaire);
        Inscription saved = repository.save(inscription);
        kafkaTemplate.send("inscription-validee-directeur", saved.getId().toString(), saved);
        return saved;
    }

    public Inscription validerAdmin(Long id, String commentaire) {
        Inscription inscription = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvee"));
        inscription.setStatut(Inscription.StatutInscription.VALIDEE_ADMIN);
        inscription.setCommentaireAdmin(commentaire);
        Inscription saved = repository.save(inscription);
        kafkaTemplate.send("inscription-validee-admin", saved.getId().toString(), saved);
        return saved;
    }

    public Inscription rejeter(Long id, String commentaire) {
        Inscription inscription = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvee"));
        inscription.setStatut(Inscription.StatutInscription.REJETEE);
        inscription.setCommentaireAdmin(commentaire);
        return repository.save(inscription);
    }

    public List<Inscription> toutes() { return repository.findAll(); }

    public Inscription trouverParId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvee"));
    }

    public List<Inscription> parDoctorant(Long doctorantId) {
        return repository.findByDoctorantId(doctorantId);
    }

    public List<Inscription> parDirecteur(String email) {
        return repository.findByDirecteurEmail(email);
    }
}