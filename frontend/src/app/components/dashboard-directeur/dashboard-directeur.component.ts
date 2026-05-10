import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InscriptionService } from '../../services/inscription.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard-directeur',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-directeur.component.html',
  styleUrl: './dashboard-directeur.component.scss'
})
export class DashboardDirecteurComponent implements OnInit {
  inscriptions: any[] = [];
  message = '';

  constructor(
    private inscriptionService: InscriptionService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.chargerInscriptions();
  }

  chargerInscriptions(): void {
    this.inscriptionService.getAll().subscribe({
      next: (data) => this.inscriptions = data,
      error: (err) => console.error(err)
    });
  }

  valider(id: number): void {
    this.inscriptionService.validerDirecteur(id, 'Validé par directeur').subscribe({
      next: () => {
        this.message = 'Inscription validée !';
        this.chargerInscriptions();
      }
    });
  }

  rejeter(id: number): void {
    this.inscriptionService.rejeter(id, 'Rejeté par directeur').subscribe({
      next: () => {
        this.message = 'Inscription rejetée.';
        this.chargerInscriptions();
      }
    });
  }
}