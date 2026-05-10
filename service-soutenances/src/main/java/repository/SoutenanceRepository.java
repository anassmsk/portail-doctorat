package com.doctorat.service_soutenances.repository;

import com.doctorat.service_soutenances.entity.Soutenance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SoutenanceRepository extends JpaRepository<Soutenance, Long> {
    List<Soutenance> findByDoctorantId(Long doctorantId);
    List<Soutenance> findByDirecteurEmail(String email);
}