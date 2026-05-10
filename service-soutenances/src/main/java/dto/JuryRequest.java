package com.doctorat.service_soutenances.dto;

import java.util.List;

public class JuryRequest {

    private List<MembreJuryDto> membres;

    public List<MembreJuryDto> getMembres() { return membres; }
    public void setMembres(List<MembreJuryDto> membres) { this.membres = membres; }

    public static class MembreJuryDto {
        private String nom;
        private String email;
        private String role; // DIRECTEUR, RAPPORTEUR, EXAMINATEUR

        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}