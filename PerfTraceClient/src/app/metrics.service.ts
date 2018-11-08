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

  getCurrentUsersNumber(): Observable<number> {
    const url = `${this.metricsUrl}/currentUsersNumber`;
    return this.http.get<number>(url);
  }
}
