import { Injectable } from '@angular/core';
import { VaccinationCenter } from './vaccination-center';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VaccinationService {
  editCenter(center: VaccinationCenter) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      observe: 'response'
    });


    const body = new HttpParams()
    .set('id', center.idCentre)
    .set('name', center.name)
    .set('address', center.address)
    .set('city', center.city)

    return this.httpClient.put<VaccinationCenter>("api/private/centers",body,{headers});    
  }
  

  constructor(private httpClient : HttpClient) { }

  getAllVaccinationCenter(city: string): Observable<VaccinationCenter[]> {
    return this.httpClient.get<VaccinationCenter[]>("api/public/centers",{
      params:{
        "city":city
      }
    }); 
  }

  postCenter(center: VaccinationCenter) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      observe: 'response'
    });

    const body = new HttpParams()
    .set('name', center.name)
    .set('address', center.address)
    .set('city', center.city)

    
    return this.httpClient.post<VaccinationCenter>("api/private/centers",body,{headers}); 
  }

  getAllVaccinationCenterById(id: number) {
    return this.httpClient.get<VaccinationCenter>("api/public/centers/detail/"+id); 
  }

  dellCenter(idCentre: number) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      observe: 'response'
    });

    const body = new HttpParams()
    .set('idCenter', idCentre)

    return this.httpClient.delete("api/private/center/"+idCentre,{headers}); 
  }
}
