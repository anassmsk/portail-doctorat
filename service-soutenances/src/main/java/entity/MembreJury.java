package com.doctorat.service_soutenances.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "membres_jury")
public class MembreJury {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long soutenanceId;
    private String nom;
    private String email;
    private String role; // DIRECTEUR, RAPPORTEUR, EXAMINATEUR

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSoutenanceId() { return soutenanceId; }
    public void setSoutenanceId(Long soutenanceId) { this.soutenanceId = soutenanceId; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}