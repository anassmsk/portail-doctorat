package com.doctorat.service_utilisateurs.service;

import com.doctorat.service_utilisateurs.dto.RegisterRequest;
import com.doctorat.service_utilisateurs.entity.Utilisateur;
import com.doctorat.service_utilisateurs.entity.Utilisateur.Role;
import com.doctorat.service_utilisateurs.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur creerUtilisateur(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email deja utilise");
        }
        Utilisateur u = new Utilisateur();
        u.setNom(request.getNom());
        u.setPrenom(request.getPrenom());
        u.setEmail(request.getEmail());
        u.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        Role role = request.getRole() != null ? request.getRole() : Role.DOCTORANT;
        u.setRole(role);
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