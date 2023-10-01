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
    dateRDV:['', [Validators.required]],
  })

  submitted = false;
  full = false;

  constructor(private formBuilder: FormBuilder, public datepipe: DatePipe) {
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.patientInfo)
    if (this.patientInfo.invalid) {
      this.full = true;
    }else{
      let priseRDV =  new Date(this.patientInfo.value.dateRDV!)
      let patient: Patient = {
        id: 0,
        firstName: this.patientInfo.value.firstName!,
        lastName: this.patientInfo.value.lastName!,
        email: this.patientInfo.value.email!,
        dateRDV: this.datepipe.transform(priseRDV,'dd.MM.yyyy')!
      }
      this.send.emit(patient)
    }

    setTimeout(() =>{ 
      this.full = false; 
    }, 3500);
    
  }

}
