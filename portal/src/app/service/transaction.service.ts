import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Report } from '../model/report';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  url : string = "http://localhost:4200/api?"

  constructor(private http: HttpClient) {}

  public getReport(page: number, size : number = 5): Observable<Report[]> {
    let endPoint = `${this.url}size=${size}&page=${page}`
    return this.http.get<Report[]>(endPoint);
  }

}
