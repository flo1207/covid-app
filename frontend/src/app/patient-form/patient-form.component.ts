import { Component, EventEmitter, Output } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import { PatientService } from '../patient.service';
import { Patient } from '../patient';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.scss']
})
export class PatientFormComponent {
  @Output() send = new EventEmitter<Patient>();

  patientInfo = this.formBuilder.group({
    firstName: ['', [Validators.required]],
    lastName: ['', [Validators.required]],
    email: ['', [Validators.required]],
    dateRDV:[Date, [Validators.required]],
  })


  constructor(private formBuilder: FormBuilder) {
  }

  onSubmit() {
    let patient: Patient = {
      id: 0,
      firstName: this.patientInfo.value.firstName!,
      lastName: this.patientInfo.value.lastName!,
      email: this.patientInfo.value.email!,
      dateRDV: this.patientInfo.value.dateRDV!.toLocaleString()
    }
    this.send.emit(patient)
  }

}
