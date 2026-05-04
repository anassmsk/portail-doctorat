package com.doctorat.service_utilisateurs.controller;

import com.doctorat.service_utilisateurs.dto.RegisterRequest;
import com.doctorat.service_utilisateurs.entity.Utilisateur;
import com.doctorat.service_utilisateurs.service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService service;

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