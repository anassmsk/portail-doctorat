package com.doctorat.service_notifications.service;

import com.doctorat.service_notifications.entity.Notification;
import com.doctorat.service_notifications.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification creer(String email, String sujet, String message, String type) {
        Notification n = new Notification();
        n.setDestinataireEmail(email);
        n.setSujet(sujet);
        n.setMessage(message);
        n.setType(type);
        return notificationRepository.save(n);
    }

    public List<Notification> getByEmail(String email) {
        return notificationRepository.findByDestinataireEmail(email);
    }

    public List<Notification> getNonLues(String email) {
        return notificationRepository.findByDestinataireEmailAndLu(email, false);
    }

    public Notification marquerLue(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification non trouvee"));
        n.setLu(true);
        return notificationRepository.save(n);
    }

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }
}