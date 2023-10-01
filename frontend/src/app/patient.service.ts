import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from './patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private httpClient : HttpClient) { }
  postPatient(patient: Patient, id: number) {
    console.log(patient.email)
    const body = new HttpParams()
  .set('mail', patient.email)
  .set('id_centre', id)
  .set('nom', patient.lastName)
  .set('prenom', patient.firstName);

  this.httpClient.post("api/public/centers/patients", body, {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded'),
    observe: 'response'
  }).subscribe(response => console.log(response.status));

  }

}

// next: data => {
//   this.postId = data.id;
// },
// error: error => {
//   this.errorMessage = error.message;
//   console.error('There was an error!', error);
// }
