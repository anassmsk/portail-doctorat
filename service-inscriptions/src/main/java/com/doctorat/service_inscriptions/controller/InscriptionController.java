package com.doctorat.service_inscriptions.controller;

import com.doctorat.service_inscriptions.dto.InscriptionRequest;
import com.doctorat.service_inscriptions.entity.Inscription;
import com.doctorat.service_inscriptions.service.InscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    private final InscriptionService service;

    public InscriptionController(InscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Inscription> soumettre(@Valid @RequestBody InscriptionRequest request) {
        return ResponseEntity.ok(service.soumettre(request));
    }

    @GetMapping
    public ResponseEntity<List<Inscription>> getAll() {
        return ResponseEntity.ok(service.toutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscription> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.trouverParId(id));
    }

    @GetMapping("/doctorant/{id}")
    public ResponseEntity<List<Inscription>> parDoctorant(@PathVariable Long id) {
        return ResponseEntity.ok(service.parDoctorant(id));
    }

    @GetMapping("/directeur/{email}")
    public ResponseEntity<List<Inscription>> parDirecteur(@PathVariable String email) {
        return ResponseEntity.ok(service.parDirecteur(email));
    }

    @PutMapping("/{id}/valider-directeur")
    public ResponseEntity<Inscription> validerDirecteur(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.validerDirecteur(id, body.get("commentaire")));
    }

    @PutMapping("/{id}/valider-admin")
    public ResponseEntity<Inscription> validerAdmin(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.validerAdmin(id, body.get("commentaire")));
    }

    @PutMapping("/{id}/rejeter")
    public ResponseEntity<Inscription> rejeter(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.rejeter(id, body.get("commentaire")));
    }
}