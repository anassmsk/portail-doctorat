package com.doctorat.service_utilisateurs.service;

import com.doctorat.service_utilisateurs.dto.RegisterRequest;
import com.doctorat.service_utilisateurs.entity.Utilisateur;
import com.doctorat.service_utilisateurs.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Utilisateur creerUtilisateur(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email deja utilise");
        }
        Utilisateur u = new Utilisateur();
        u.setNom(request.getNom());
        u.setPrenom(request.getPrenom());
        u.setEmail(request.getEmail());
        u.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        u.setRole(request.getRole() != null ? request.getRole() : Utilisateur.Role.DOCTORANT);
        return repository.save(u);
    }

    public List<Utilisateur> tousLesUtilisateurs() {
        return repository.findAll();
    }

    public Utilisateur trouverParEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve"));
    }
}