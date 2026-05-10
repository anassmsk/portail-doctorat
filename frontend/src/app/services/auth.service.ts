import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router) {}

  login(email: string, motDePasse: string): void {
    // Simulation login - à remplacer par appel API réel
    if (email.includes('admin')) {
      localStorage.setItem('role', 'ADMIN');
      localStorage.setItem('email', email);
      this.router.navigate(['/admin']);
    } else if (email.includes('directeur')) {
      localStorage.setItem('role', 'DIRECTEUR_THESE');
      localStorage.setItem('email', email);
      this.router.navigate(['/directeur']);
    } else {
      localStorage.setItem('role', 'DOCTORANT');
      localStorage.setItem('email', email);
      this.router.navigate(['/doctorant']);
    }
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  getRole(): string {
    return localStorage.getItem('role') || '';
  }

  getEmail(): string {
    return localStorage.getItem('email') || '';
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('role');
  }
}