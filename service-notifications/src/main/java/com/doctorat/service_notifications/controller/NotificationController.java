package com.doctorat.service_notifications.controller;

import com.doctorat.service_notifications.entity.Notification;
import com.doctorat.service_notifications.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Notification>> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(notificationService.getByEmail(email));
    }

    @GetMapping("/non-lues/{email}")
    public ResponseEntity<List<Notification>> getNonLues(@PathVariable String email) {
        return ResponseEntity.ok(notificationService.getNonLues(email));
    }

    @PutMapping("/{id}/lue")
    public ResponseEntity<Notification> marquerLue(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.marquerLue(id));
    }
}