package com.doctorat.service_inscriptions.controller;

import com.doctorat.service_inscriptions.dto.InscriptionRequest;
import com.doctorat.service_inscriptions.entity.Inscription;
import com.doctorat.service_inscriptions.service.InscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Inscription>> toutes() {
        return ResponseEntity.ok(service.toutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscription> parId(@PathVariable Long id) {
        return ResponseEntity.ok(service.trouverParId(id));
    }

    @GetMapping("/doctorant/{doctorantId}")
    public ResponseEntity<List<Inscription>> parDoctorant(@PathVariable Long doctorantId) {
        return ResponseEntity.ok(service.parDoctorant(doctorantId));
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