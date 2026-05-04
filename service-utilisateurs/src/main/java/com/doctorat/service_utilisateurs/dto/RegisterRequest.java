package com.doctorat.service_utilisateurs.dto;

import com.doctorat.service_utilisateurs.entity.Utilisateur;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String motDePasse;

    private Utilisateur.Role role;
}