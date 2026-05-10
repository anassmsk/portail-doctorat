package com.doctorat.service_soutenances.repository;

import com.doctorat.service_soutenances.entity.MembreJury;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembreJuryRepository extends JpaRepository<MembreJury, Long> {
    List<MembreJury> findBySoutenanceId(Long soutenanceId);
}