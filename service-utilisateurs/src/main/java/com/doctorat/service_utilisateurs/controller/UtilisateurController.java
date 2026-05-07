package com.doctorat.service_utilisateurs.controller;

import com.doctorat.service_utilisateurs.dto.RegisterRequest;
import com.doctorat.service_utilisateurs.entity.Utilisateur;
import com.doctorat.service_utilisateurs.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.creerUtilisateur(request));
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> tous() {
        return ResponseEntity.ok(service.tousLesUtilisateurs());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Utilisateur> parEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.trouverParEmail(email));
    }
}