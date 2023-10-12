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
  @Input() gestion?: boolean;
  @Output() deleted = new EventEmitter<VaccinationCenter>();
  @Output() edited = new EventEmitter<VaccinationCenter>;

  selected?: Boolean;
  confirm?: Boolean;
  error?: Boolean;
  message = "Réservation prise en compte !";

  nb_reserv = 0
  conf = false;



  constructor(private route: ActivatedRoute, private servicePatient: PatientService, private service: VaccinationService){ }

  ngOnInit(): void {
    this.nb_reserv = this.center!.patients.length;
    console.log(this.gestion)

  }

  deselect() {
    this.selected = false;
  }

  centerSelect() {
    this.selected = !this.selected;
  }

  async sendPatient(patient: Patient) {
    const send = (await this.servicePatient.postPatient(patient, this.center!.idCentre)).subscribe((response) => {
        this.confirm = true;
        this.nb_reserv += 1;
    },
    (error: HttpErrorResponse) => {
      this.message = "Une erreur est survenue, "+error.message;
      this.error = true;
    });

    this.selected = false;
    setTimeout(() =>{ 
        this.confirm = false; 
      }, 3000);
    
  }

  supCenter(){
    this.service.dellCenter(this.center!.idCentre).subscribe(() =>{
      this.deleted.emit(this.center)
    });
  }

  setConf(){
    this.conf = true;
    setTimeout(() =>{ 
      this.conf = false; 
    }, 3000);
  }

  modifierCentre(){
    this.edited.emit(this.center);
  }
      
}
