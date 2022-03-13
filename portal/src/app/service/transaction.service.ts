import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { ResponseReport } from '../model/responseReport';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  url : string = "http://localhost:4200/api?"

  constructor(private http: HttpClient) {}

  public getReport(page: number, startDate: string, endDate:  string, size : number = 5): Observable<ResponseReport> {
    let endPoint = `${this.url}size=${size}&page=${page}&startDate=${startDate}&endDate=${endDate}`
    return this.http.get<ResponseReport>(endPoint);
  }

}
