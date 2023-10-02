import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from './patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private httpClient : HttpClient) { }

  async postPatient(patient: Patient, id: number) {
    const body = new HttpParams()
    .set('mail', patient.email)
    .set('id_centre', id)
    .set('nom', patient.lastName)
    .set('localDate', patient.dateRDV)
    .set('prenom', patient.firstName);
  
    return await this.httpClient.post("api/public/centers/patients", body, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded'),
      observe: 'response'
    })

  }

}


