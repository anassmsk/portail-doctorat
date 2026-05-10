import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InscriptionService } from '../../services/inscription.service';
import { SoutenanceService } from '../../services/soutenance.service';

@Component({
  selector: 'app-dashboard-admin',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-admin.component.html',
  styleUrl: './dashboard-admin.component.scss'
})
export class DashboardAdminComponent implements OnInit {
  inscriptions: any[] = [];
  soutenances: any[] = [];
  message = '';

  constructor(
    private inscriptionService: InscriptionService,
    private soutenanceService: SoutenanceService
  ) {}

  ngOnInit(): void {
    this.chargerInscriptions();
    this.chargerSoutenances();
  }

  chargerInscriptions(): void {
    this.inscriptionService.getAll().subscribe({
      next: (data) => this.inscriptions = data,
      error: (err) => console.error(err)
    });
  }

  chargerSoutenances(): void {
    this.soutenanceService.getAll().subscribe({
      next: (data) => this.soutenances = data,
      error: (err) => console.error(err)
    });
  }

  validerInscription(id: number): void {
    this.inscriptionService.validerAdmin(id, 'Validé par admin').subscribe({
      next: () => {
        this.message = 'Inscription validée !';
        this.chargerInscriptions();
      }
    });
  }

  rejeterInscription(id: number): void {
    this.inscriptionService.rejeter(id, 'Rejeté par admin').subscribe({
      next: () => {
        this.message = 'Inscription rejetée.';
        this.chargerInscriptions();
      }
    });
  }

  validerSoutenance(id: number): void {
    this.soutenanceService.validerAdmin(id, 'Validé par admin').subscribe({
      next: () => {
        this.message = 'Soutenance validée !';
        this.chargerSoutenances();
      }
    });
  }
}