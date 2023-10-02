import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { ActivatedRoute } from '@angular/router';
import { VaccinationService } from '../vaccination.service';
import { PatientService } from '../../patient/patient.service';
import { Patient } from '../../patient/patient';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-vaccination-center',
  templateUrl: './vaccination-center.component.html',
  styleUrls: ['./vaccination-center.component.scss']
})
export class VaccinationCenterComponent implements OnInit{

  @Input() center?: VaccinationCenter;
  @Output() deleted = new EventEmitter<VaccinationCenter>();

  selected?: Boolean;
  confirm?: Boolean;
  error?: Boolean;
  message = "Réservation prise en compte !";

  nb_reserv = 0


  constructor(private route: ActivatedRoute, private servicePatient: PatientService){ }

  ngOnInit(): void {
    this.nb_reserv = this.center!.patients.length;
  }

  delete(){
    this.deleted.emit(this.center)
  }

  deselect() {
    this.selected = false;
  }

  centerSelect() {
    this.selected = !this.selected;
  }

  async sendPatient(patient: Patient) {
    const send = (await this.servicePatient.postPatient(patient, this.center!.id)).subscribe((response) => {
        console.log(response.status);
        this.confirm = true;
        this.nb_reserv += 1;
    },
    (error: HttpErrorResponse) => {
      this.message = "Une erreur est survenue, "+error.message;
      this.error = true;
      console.log(error.status)
    });

    this.selected = false;
    setTimeout(() =>{ 
        this.confirm = false; 
      }, 3000);
    
  }
      
}
