import { Component , Input} from '@angular/core';
import { Patient } from '../patient';
import { PatientService } from '../patient.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent {
  @Input() patient?: Patient;

  constructor(private patientService: PatientService){}

  async vacciner(){
    this.patient!.vaccination = true;
   ( await this.patientService.updatePatientVaccination(this.patient!.idPatient)).subscribe((response) => {
      console.log(response.status);
    },
    (error: HttpErrorResponse) => {
      console.log(error.status)
    });

  }
}
