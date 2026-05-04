# Portail de Suivi du Doctorat

Plateforme web centralisée pour dématérialiser le parcours doctoral (inscription → soutenance).

## Architecture

- **Frontend** : Angular (SPA)
- **Backend** : Micro-services Spring Boot + Spring Cloud
- **Messagerie** : Apache Kafka
- **Batch** : Spring Batch
- **Infra** : Docker, Eureka, API Gateway, Config Server

## Lancer le projet

```bash
docker-compose up -d
```

## Services

| Service | Port | Description |
|---|---|---|
| config-server | 8888 | Configuration centralisée |
| eureka-server | 8761 | Service Discovery |
| api-gateway | 8080 | Point d'entrée unique |
| service-utilisateurs | 8081 | Comptes, rôles, JWT |
| service-inscriptions | 8082 | Campagnes, dossiers |
| service-soutenances | 8083 | Prérequis, jury, PV |
| service-notifications | 8084 | Emails, PDF |
| service-batch | 8085 | Traitements planifiés |
| frontend | 4200 | Application Angular |

## Équipe

| Membre | Rôle |
|---|---|
| ... | ... |
