import { Injectable } from '@angular/core';
import { VaccinationCenter } from './vaccination-center';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VaccinationService {

  constructor(private httpClient : HttpClient) { }

  getAllVaccinationCenter(city: string): Observable<VaccinationCenter[]> {
    return this.httpClient.get<VaccinationCenter[]>("api/public/centers",{
      params:{
        "city":city
      }
    }); 
  }

  getAllVaccinationCenterById(id: number) {
    return this.httpClient.get<VaccinationCenter>("api/public/centers/detail/"+id); 
  }
}
