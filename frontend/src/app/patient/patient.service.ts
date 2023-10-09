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
    .set('mail', patient.mail)
    .set('id_centre', id)
    .set('nom', patient.nom)
    .set('localDate', patient.date_RDV)
    .set('prenom', patient.prenom);
  
    return await this.httpClient.post("api/public/centers/patients", body, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded'),
      observe: 'response'
    })

  }

  async updatePatientVaccination(id: number){
    const body = new HttpParams()
    .set('id', id)
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Basic ${token}`,
    });
  
    return await this.httpClient.patch("api/private/centers/patient", body, {headers})
  }

}


