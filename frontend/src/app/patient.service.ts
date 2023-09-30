import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from './patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private httpClient : HttpClient) { }
  postPatient(patient: Patient, id: number) {
    console.log(patient)
    const body = new HttpParams()
  .set('mail', patient.email)
  .set('id_centre', id)
  .set('nom', patient.lastName)
  .set('prenom', patient.firstName);

  this.httpClient.post("api/public/centers/patients", body, {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded')
  }).subscribe();
  //   this.httpClient.post("api/public/centers/patients",{
  //     params:{
  //       "mail":patient.email,
  //       "nom":patient.lastName,
  //       "prenom":patient.firstName,
  //       "id_centre":id
  //     }
  //   }).subscribe();
  }

}
