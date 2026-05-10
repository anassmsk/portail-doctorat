package com.doctorat.service_soutenances.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "soutenances")
public class Soutenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorantId;
    private String nomDoctorant;
    private String emailDoctorant;
    private String sujetThese;
    private String directeurEmail;

    @Enumerated(EnumType.STRING)
    private StatutSoutenance statut = StatutSoutenance.EN_ATTENTE;

    private LocalDate dateSoutenance;
    private String lieu;
    private String commentaireAdmin;
    private LocalDateTime dateCreation = LocalDateTime.now();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public StatutSoutenance getStatut() { return statut; }
    public void setStatut(StatutSoutenance statut) { this.statut = statut; }
    public LocalDate getDateSoutenance() { return dateSoutenance; }
    public void setDateSoutenance(LocalDate dateSoutenance) { this.dateSoutenance = dateSoutenance; }
    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public String getCommentaireAdmin() { return commentaireAdmin; }
    public void setCommentaireAdmin(String commentaireAdmin) { this.commentaireAdmin = commentaireAdmin; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
}