import { Component, EventEmitter, Output } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import { PatientService } from '../patient.service';
import { Patient } from '../patient';
import { DatePipe } from '@angular/common';

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
    dateRDV:[new Date(), [Validators.required]],
  })


  constructor(private formBuilder: FormBuilder, public datepipe: DatePipe) {
  }

  onSubmit() {
    let patient: Patient = {
      id: 0,
      firstName: this.patientInfo.value.firstName!,
      lastName: this.patientInfo.value.lastName!,
      email: this.patientInfo.value.email!,
      dateRDV: this.datepipe.transform(this.patientInfo.value.dateRDV!,'dd.MM.yyyy')
    }
    console.log(patient.dateRDV)
    this.send.emit(patient)
  }

}
