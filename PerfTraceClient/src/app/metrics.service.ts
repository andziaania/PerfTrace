import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MetricsService {

  private metricsUrl = 'http://localhost:8080';

  constructor(
    private http: HttpClient
  ) { }

  getCurrentUsersNumber(webappId: number): Observable<number> {
    const url = `${this.metricsUrl}/currentUsersNumber?webappId=${webappId}`;
    return this.http.get<number>(url);
  }

  getWebapps(): Observable<number[]> {
    const url = `${this.metricsUrl}/getWebappList`;
    return this.http.get<number[]>(url);
  }
}
