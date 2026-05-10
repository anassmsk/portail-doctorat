package com.doctorat.service_batch.service;

import com.doctorat.service_batch.entity.TacheBatch;
import com.doctorat.service_batch.repository.TacheRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BatchService {

    private final TacheRepository tacheRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public BatchService(TacheRepository tacheRepository,
                        KafkaTemplate<String, String> kafkaTemplate) {
        this.tacheRepository = tacheRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    // Toutes les 24h
    @Scheduled(fixedRate = 86400000)
    public void verifierDureesThese() {
        System.out.println("Batch: verification des durees de these...");
        TacheBatch tache = new TacheBatch();
        tache.setType("DUREE_DEPASSEE");
        tache.setDescription("Verification durees theses - " + LocalDateTime.now());
        tache.setStatut("TRAITE");
        tache.setDateTraitement(LocalDateTime.now());
        tacheRepository.save(tache);
        try {
            kafkaTemplate.send("batch-duree-depassee", "Verification durees: " + LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Kafka non disponible: " + e.getMessage());
        }
    }

    // Toutes les 48h
    @Scheduled(fixedRate = 172800000)
    public void envoyerRelances() {
        System.out.println("Batch: envoi des relances...");
        TacheBatch tache = new TacheBatch();
        tache.setType("RELANCE");
        tache.setDescription("Relances automatiques - " + LocalDateTime.now());
        tache.setStatut("TRAITE");
        tache.setDateTraitement(LocalDateTime.now());
        tacheRepository.save(tache);
        try {
            kafkaTemplate.send("batch-relance", "Relances: " + LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Kafka non disponible: " + e.getMessage());
        }
    }

    // Tous les lundis à 8h
    @Scheduled(cron = "0 0 8 * * MON")
    public void genererRapportHebdomadaire() {
        System.out.println("Batch: generation du rapport hebdomadaire...");
        TacheBatch tache = new TacheBatch();
        tache.setType("RAPPORT");
        tache.setDescription("Rapport hebdomadaire - " + LocalDateTime.now());
        tache.setStatut("TRAITE");
        tache.setDateTraitement(LocalDateTime.now());
        tacheRepository.save(tache);
        try {
            kafkaTemplate.send("batch-rapport", "Rapport: " + LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Kafka non disponible: " + e.getMessage());
        }
    }

    public List<TacheBatch> getAll() { return tacheRepository.findAll(); }
    public List<TacheBatch> getEnAttente() { return tacheRepository.findByStatut("EN_ATTENTE"); }
}