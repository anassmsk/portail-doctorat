import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SoutenanceService {

  private apiUrl = 'http://localhost:8083/api/soutenances';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getByDoctorant(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/doctorant/${id}`);
  }

  soumettre(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }

  validerAdmin(id: number, commentaire: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/valider-admin`, { commentaire });
  }

  planifier(id: number, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/planifier`, data);
  }
}