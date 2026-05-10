import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InscriptionService } from '../../services/inscription.service';
import { SoutenanceService } from '../../services/soutenance.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard-doctorant',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard-doctorant.component.html',
  styleUrl: './dashboard-doctorant.component.scss'
})
export class DashboardDoctorantComponent implements OnInit {
  inscriptions: any[] = [];
  soutenances: any[] = [];
  nouvelleInscription: any = {};
  afficherFormInscription = false;
  message = '';

  constructor(
    private inscriptionService: InscriptionService,
    private soutenanceService: SoutenanceService,
    public authService: AuthService
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

  soumettrInscription(): void {
    this.nouvelleInscription.emailDoctorant = this.authService.getEmail();
    this.inscriptionService.soumettre(this.nouvelleInscription).subscribe({
      next: () => {
        this.message = 'Inscription soumise avec succès !';
        this.afficherFormInscription = false;
        this.nouvelleInscription = {};
        this.chargerInscriptions();
      },
      error: () => this.message = 'Erreur lors de la soumission.'
    });
  }
}