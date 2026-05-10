package com.doctorat.service_soutenances.service;

import com.doctorat.service_soutenances.dto.JuryRequest;
import com.doctorat.service_soutenances.dto.PlanificationRequest;
import com.doctorat.service_soutenances.dto.SoutenanceRequest;
import com.doctorat.service_soutenances.entity.MembreJury;
import com.doctorat.service_soutenances.entity.Soutenance;
import com.doctorat.service_soutenances.entity.StatutSoutenance;
import com.doctorat.service_soutenances.repository.MembreJuryRepository;
import com.doctorat.service_soutenances.repository.SoutenanceRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoutenanceService {

    private final SoutenanceRepository soutenanceRepository;
    private final MembreJuryRepository membreJuryRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SoutenanceService(SoutenanceRepository soutenanceRepository,
                             MembreJuryRepository membreJuryRepository,
                             KafkaTemplate<String, String> kafkaTemplate) {
        this.soutenanceRepository = soutenanceRepository;
        this.membreJuryRepository = membreJuryRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Soutenance soumettre(SoutenanceRequest request) {
        Soutenance s = new Soutenance();
        s.setDoctorantId(request.getDoctorantId());
        s.setNomDoctorant(request.getNomDoctorant());
        s.setEmailDoctorant(request.getEmailDoctorant());
        s.setSujetThese(request.getSujetThese());
        s.setDirecteurEmail(request.getDirecteurEmail());
        Soutenance saved = soutenanceRepository.save(s);
        kafkaTemplate.send("soutenance-soumise", "Soutenance soumise: " + saved.getId());
        return saved;
    }

    public Soutenance proposerJury(Long id, JuryRequest request) {
        Soutenance s = soutenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soutenance non trouvee"));
        for (JuryRequest.MembreJuryDto dto : request.getMembres()) {
            MembreJury m = new MembreJury();
            m.setSoutenanceId(id);
            m.setNom(dto.getNom());
            m.setEmail(dto.getEmail());
            m.setRole(dto.getRole());
            membreJuryRepository.save(m);
        }
        s.setStatut(StatutSoutenance.JURY_PROPOSE);
        Soutenance saved = soutenanceRepository.save(s);
        kafkaTemplate.send("soutenance-jury-propose", "Jury propose pour: " + id);
        return saved;
    }

    public Soutenance validerAdmin(Long id, String commentaire) {
        Soutenance s = soutenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soutenance non trouvee"));
        s.setStatut(StatutSoutenance.VALIDEE_ADMIN);
        s.setCommentaireAdmin(commentaire);
        Soutenance saved = soutenanceRepository.save(s);
        kafkaTemplate.send("soutenance-validee-admin", "Soutenance validee: " + id);
        return saved;
    }

    public Soutenance planifier(Long id, PlanificationRequest request) {
        Soutenance s = soutenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soutenance non trouvee"));
        s.setStatut(StatutSoutenance.PLANIFIEE);
        s.setDateSoutenance(request.getDateSoutenance());
        s.setLieu(request.getLieu());
        Soutenance saved = soutenanceRepository.save(s);
        kafkaTemplate.send("soutenance-planifiee", "Soutenance planifiee: " + id);
        return saved;
    }

    public Soutenance marquerSoutenue(Long id) {
        Soutenance s = soutenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soutenance non trouvee"));
        s.setStatut(StatutSoutenance.SOUTENUE);
        Soutenance saved = soutenanceRepository.save(s);
        kafkaTemplate.send("soutenance-soutenue", "Soutenance effectuee: " + id);
        return saved;
    }

    public List<Soutenance> getAll() { return soutenanceRepository.findAll(); }
    public Soutenance getById(Long id) { return soutenanceRepository.findById(id).orElseThrow(); }
    public List<Soutenance> getByDoctorant(Long id) { return soutenanceRepository.findByDoctorantId(id); }
    public List<Soutenance> getByDirecteur(String email) { return soutenanceRepository.findByDirecteurEmail(email); }
    public List<MembreJury> getJury(Long id) { return membreJuryRepository.findBySoutenanceId(id); }
}