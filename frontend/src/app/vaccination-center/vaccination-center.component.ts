import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { ActivatedRoute } from '@angular/router';
import { VaccinationService } from '../vaccination.service';
import { PatientService } from '../patient.service';
import { Patient } from '../patient';

@Component({
  selector: 'app-vaccination-center',
  templateUrl: './vaccination-center.component.html',
  styleUrls: ['./vaccination-center.component.scss']
})
export class VaccinationCenterComponent implements OnInit{

  @Input() center?: VaccinationCenter;
  @Output() deleted = new EventEmitter<VaccinationCenter>();

  selected?: Boolean;


  constructor(private route: ActivatedRoute, private servicePatient: PatientService){ }

  ngOnInit(): void {
    console.log(this.center);
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

  sendPatient(patient: Patient) {
    this.servicePatient.postPatient(patient, this.center!.id)
  }
      
}
