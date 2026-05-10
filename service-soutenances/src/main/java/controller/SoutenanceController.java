package com.doctorat.service_soutenances.controller;

import com.doctorat.service_soutenances.dto.JuryRequest;
import com.doctorat.service_soutenances.dto.PlanificationRequest;
import com.doctorat.service_soutenances.dto.SoutenanceRequest;
import com.doctorat.service_soutenances.entity.MembreJury;
import com.doctorat.service_soutenances.entity.Soutenance;
import com.doctorat.service_soutenances.service.SoutenanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/soutenances")
public class SoutenanceController {

    private final SoutenanceService soutenanceService;

    public SoutenanceController(SoutenanceService soutenanceService) {
        this.soutenanceService = soutenanceService;
    }

    @PostMapping
    public ResponseEntity<Soutenance> soumettre(@Valid @RequestBody SoutenanceRequest request) {
        return ResponseEntity.ok(soutenanceService.soumettre(request));
    }

    @GetMapping
    public ResponseEntity<List<Soutenance>> getAll() {
        return ResponseEntity.ok(soutenanceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soutenance> getById(@PathVariable Long id) {
        return ResponseEntity.ok(soutenanceService.getById(id));
    }

    @GetMapping("/doctorant/{id}")
    public ResponseEntity<List<Soutenance>> getByDoctorant(@PathVariable Long id) {
        return ResponseEntity.ok(soutenanceService.getByDoctorant(id));
    }

    @GetMapping("/directeur/{email}")
    public ResponseEntity<List<Soutenance>> getByDirecteur(@PathVariable String email) {
        return ResponseEntity.ok(soutenanceService.getByDirecteur(email));
    }

    @GetMapping("/{id}/jury")
    public ResponseEntity<List<MembreJury>> getJury(@PathVariable Long id) {
        return ResponseEntity.ok(soutenanceService.getJury(id));
    }

    @PostMapping("/{id}/jury")
    public ResponseEntity<Soutenance> proposerJury(@PathVariable Long id,
                                                   @RequestBody JuryRequest request) {
        return ResponseEntity.ok(soutenanceService.proposerJury(id, request));
    }

    @PutMapping("/{id}/valider-admin")
    public ResponseEntity<Soutenance> validerAdmin(@PathVariable Long id,
                                                   @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(soutenanceService.validerAdmin(id, body.get("commentaire")));
    }

    @PutMapping("/{id}/planifier")
    public ResponseEntity<Soutenance> planifier(@PathVariable Long id,
                                                @Valid @RequestBody PlanificationRequest request) {
        return ResponseEntity.ok(soutenanceService.planifier(id, request));
    }

    @PutMapping("/{id}/soutenue")
    public ResponseEntity<Soutenance> marquerSoutenue(@PathVariable Long id) {
        return ResponseEntity.ok(soutenanceService.marquerSoutenue(id));
    }
}