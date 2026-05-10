import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

  private apiUrl = 'http://localhost:8082/api/inscriptions';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getByDoctorant(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/doctorant/${id}`);
  }

  getByDirecteur(email: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/directeur/${email}`);
  }

  soumettre(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }

  validerDirecteur(id: number, commentaire: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/valider-directeur`, { commentaire });
  }

  validerAdmin(id: number, commentaire: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/valider-admin`, { commentaire });
  }

  rejeter(id: number, commentaire: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/rejeter`, { commentaire });
  }
}