import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardDoctorantComponent } from './components/dashboard-doctorant/dashboard-doctorant.component';
import { DashboardDirecteurComponent } from './components/dashboard-directeur/dashboard-directeur.component';
import { DashboardAdminComponent } from './components/dashboard-admin/dashboard-admin.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'doctorant', component: DashboardDoctorantComponent },
  { path: 'directeur', component: DashboardDirecteurComponent },
  { path: 'admin', component: DashboardAdminComponent },
  { path: '**', redirectTo: 'login' }
];