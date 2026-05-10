package com.doctorat.service_inscriptions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscriptions")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long doctorantId;

    @Column(nullable = false)
    private String nomDoctorant;

    @Column(nullable = false)
    private String emailDoctorant;

    @Column(nullable = false)
    private String sujetThese;

    @Column(nullable = false)
    private String directeurEmail;

    private String collaboration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutInscription statut = StatutInscription.EN_ATTENTE;

    @Column(nullable = false)
    private Integer annee;

    private LocalDate dateInscription = LocalDate.now();
    private LocalDateTime dateCreation = LocalDateTime.now();
    private String commentaireDirecteur;
    private String commentaireAdmin;

    public enum StatutInscription {
        EN_ATTENTE, VALIDEE_DIRECTEUR, VALIDEE_ADMIN, REJETEE
    }

    public Long getId() { return id; }
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
    public String getCollaboration() { return collaboration; }
    public void setCollaboration(String collaboration) { this.collaboration = collaboration; }
    public StatutInscription getStatut() { return statut; }
    public void setStatut(StatutInscription statut) { this.statut = statut; }
    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }
    public LocalDate getDateInscription() { return dateInscription; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public String getCommentaireDirecteur() { return commentaireDirecteur; }
    public void setCommentaireDirecteur(String commentaireDirecteur) { this.commentaireDirecteur = commentaireDirecteur; }
    public String getCommentaireAdmin() { return commentaireAdmin; }
    public void setCommentaireAdmin(String commentaireAdmin) { this.commentaireAdmin = commentaireAdmin; }
}