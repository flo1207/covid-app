import { Component, Input, OnInit, PipeTransform} from '@angular/core';
import { Patient } from '../patient';
import { PatientService } from '../patient.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Observable, map, startWith } from 'rxjs';
import { DecimalPipe } from '@angular/common';
import { FormControl } from '@angular/forms';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.scss'],
  providers: [DecimalPipe],
})

export class PatientListComponent implements OnInit{
  @Input() patients?: Patient[];

  patients_filter?: Patient[];

  patients$: Observable<Patient[]>;

	filter = new FormControl('', { nonNullable: true });

  today : Date = new Date();
  date_RDV: Date | undefined;

  calendar = false;

	constructor(private patientService: PatientService, pipe: DecimalPipe) {
		this.patients$ = this.filter.valueChanges.pipe(
			startWith(''),
			map((text) => this.search(text, pipe)),
		);
    

  }
  ngOnInit(): void {
    this.patients_filter = this.patients;
    this.filtreByDate()
  }

  async vacciner(patient: Patient){
    
    this.patients!.forEach(item =>{
      if(item.idPatient == patient.idPatient){
          item.vaccination = true
      }
    });
   ( await this.patientService.updatePatientVaccination(patient.idPatient)).subscribe((response) => {
      console.log(response);
    },
    (error: HttpErrorResponse) => {
      console.log(error.status)
    });

  }
  
  search(text: string, pipe: PipeTransform): Patient[] {
    return this.patients!.filter((patient) => {
      const term = text.toLowerCase();
      return (
        patient.nom.toLowerCase().includes(term) ||
        patient.prenom.toLowerCase().includes(term) ||
        patient.mail.toLowerCase().includes(term)
      );
    });
  }

  dateUp(){
    var res = new Date(this.today);
    res.setDate(res.getDate() + 1);
    this.today = res;
    this.filtreByDate()
  }

  dateDown(){
    var res = new Date(this.today);
    res.setDate(res.getDate() - 1);
    this.today = res;
    this.filtreByDate()
  }

  isDate(date : string){
    var new_date = new Date(date);
    
    var res = (new_date.getDate() == this.today.getDate() && new_date.getMonth() == this.today.getMonth() && new_date.getFullYear() == this.today.getFullYear())
    return res;
  }

  filtreByDate(){
    this.patients = this.patients_filter?.filter( pat => this.isDate(pat.date_RDV))
    this.filter.setValue("");
  }

  onDateSelect(date:NgbDate){
    this.today = new Date(date.year, date.month - 1, date.day);
    this.filtreByDate();
  }

  showCal(){
    this.calendar = !this.calendar;
  }


}


