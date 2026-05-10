package com.doctorat.service_soutenances.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SoutenanceRequest {

    @NotNull
    private Long doctorantId;
    @NotBlank
    private String nomDoctorant;
    @Email @NotBlank
    private String emailDoctorant;
    @NotBlank
    private String sujetThese;
    @Email @NotBlank
    private String directeurEmail;

    public Long getDoctorantId() { return doctorantId; }
    public void setDoctorantId(Long doctorantId) { this.doctorantId = doctorantId; }
    public String getNomDoctorant() { return nomDoctorant; }
    public void setNomDoctorant(String nomDoctorant) { this.nomDoctorant = nomDoctorant; }
    public String getEmailDoctorant() { return emailDoctorant; }
    public void setEmailDoctorant(String emailDoctorant) { this.emailDoctorant = emailDoctorant; }
    public String getSujetThese() { return sujetThese; }
    public void setSujetThese(String sujetThese) { this.sujetThese = sujetThese; }
    public String getDirecteurEmail() { return directeurEmail; }
    public void setDirecteurEmail(String directeurEmail) { this.directeurEmail = directeurEmail; }
}