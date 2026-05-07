package com.doctorat.service_utilisateurs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean actif = true;

    private LocalDateTime dateCreation = LocalDateTime.now();

    public enum Role {
        DOCTORANT, DIRECTEUR_THESE, ADMIN
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public Role getRole() { return role; }
    public boolean isActif() { return actif; }
    public LocalDateTime getDateCreation() { return dateCreation; }

    public void setEmail(String email) { this.email = email; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setRole(Role role) { this.role = role; }
    public void setActif(boolean actif) { this.actif = actif; }
}