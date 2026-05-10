package com.doctorat.service_soutenances.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class PlanificationRequest {

    @NotNull
    private LocalDate dateSoutenance;
    @NotBlank
    private String lieu;

    public LocalDate getDateSoutenance() { return dateSoutenance; }
    public void setDateSoutenance(LocalDate dateSoutenance) { this.dateSoutenance = dateSoutenance; }
    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
}