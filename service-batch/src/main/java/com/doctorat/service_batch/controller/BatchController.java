package com.doctorat.service_batch.controller;

import com.doctorat.service_batch.entity.TacheBatch;
import com.doctorat.service_batch.service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/batch")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/taches")
    public ResponseEntity<List<TacheBatch>> getAll() {
        return ResponseEntity.ok(batchService.getAll());
    }

    @GetMapping("/taches/en-attente")
    public ResponseEntity<List<TacheBatch>> getEnAttente() {
        return ResponseEntity.ok(batchService.getEnAttente());
    }
}