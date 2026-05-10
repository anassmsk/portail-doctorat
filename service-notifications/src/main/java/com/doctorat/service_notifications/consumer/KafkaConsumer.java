package com.doctorat.service_notifications.consumer;

import com.doctorat.service_notifications.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final NotificationService notificationService;

    public KafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "inscription-soumise", groupId = "notifications-group")
    public void onInscriptionSoumise(String message) {
        System.out.println("Kafka recu [inscription-soumise]: " + message);
        notificationService.creer(
                "admin@doctorat.ma",
                "Nouvelle inscription soumise",
                message,
                "INSCRIPTION"
        );
    }

    @KafkaListener(topics = "inscription-validee-directeur", groupId = "notifications-group")
    public void onInscriptionValideeDirecteur(String message) {
        System.out.println("Kafka recu [inscription-validee-directeur]: " + message);
        notificationService.creer(
                "admin@doctorat.ma",
                "Inscription validee par directeur",
                message,
                "INSCRIPTION"
        );
    }

    @KafkaListener(topics = "inscription-validee-admin", groupId = "notifications-group")
    public void onInscriptionValideeAdmin(String message) {
        System.out.println("Kafka recu [inscription-validee-admin]: " + message);
        notificationService.creer(
                "doctorant@doctorat.ma",
                "Votre inscription a ete validee",
                message,
                "INSCRIPTION"
        );
    }

    @KafkaListener(topics = "soutenance-soumise", groupId = "notifications-group")
    public void onSoutenanceSoumise(String message) {
        System.out.println("Kafka recu [soutenance-soumise]: " + message);
        notificationService.creer(
                "admin@doctorat.ma",
                "Nouvelle soutenance soumise",
                message,
                "SOUTENANCE"
        );
    }

    @KafkaListener(topics = "soutenance-planifiee", groupId = "notifications-group")
    public void onSoutenancePlanifiee(String message) {
        System.out.println("Kafka recu [soutenance-planifiee]: " + message);
        notificationService.creer(
                "doctorant@doctorat.ma",
                "Votre soutenance a ete planifiee",
                message,
                "SOUTENANCE"
        );
    }

    @KafkaListener(topics = "soutenance-soutenue", groupId = "notifications-group")
    public void onSoutenanceSoutenue(String message) {
        System.out.println("Kafka recu [soutenance-soutenue]: " + message);
        notificationService.creer(
                "admin@doctorat.ma",
                "Soutenance effectuee",
                message,
                "SOUTENANCE"
        );
    }
}