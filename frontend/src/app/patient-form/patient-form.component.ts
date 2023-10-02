import { Component, EventEmitter, Output } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import { PatientService } from '../patient.service';
import { Patient } from '../patient';
import { DatePipe } from '@angular/common';
import { NgbCalendar, NgbDate, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

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
    dateRDV:[null, [Validators.required]],
  })

  submitted = false;
  full = false;

	date = this.calendar.getToday();
  
  
  date_RDV! : Date

  constructor(private formBuilder: FormBuilder, public datepipe: DatePipe,private calendar: NgbCalendar) {
  }  

  onDateSelect(date:NgbDate){
    this.date= date;
    this.date_RDV = new Date(this.date.year, this.date.month - 1, this.date.day);
  }

  onSubmit() {
    this.submitted = true;

    if (this.patientInfo.invalid) {
      this.full = true;
    }else{    
      let patient: Patient = {
        id: 0,
        firstName: this.patientInfo.value.firstName!,
        lastName: this.patientInfo.value.lastName!,
        email: this.patientInfo.value.email!,
        dateRDV: this.datepipe.transform(this.date_RDV,'dd.MM.yyyy')!
      }
      this.send.emit(patient);

    }

    setTimeout(() =>{ 
      this.full = false; 
    }, 3500);
    
  }

  
}

