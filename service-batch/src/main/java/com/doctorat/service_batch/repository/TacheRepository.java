package com.doctorat.service_batch.repository;

import com.doctorat.service_batch.entity.TacheBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TacheRepository extends JpaRepository<TacheBatch, Long> {
    List<TacheBatch> findByStatut(String statut);
}