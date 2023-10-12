import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { VaccinationCenter } from 'src/app/Vaccination/vaccination-center';
import { VaccinationService } from 'src/app/Vaccination/vaccination.service';

@Component({
  selector: 'app-vaccination-center-form',
  templateUrl: './vaccination-center-form.component.html',
  styleUrls: ['./vaccination-center-form.component.scss']
})
export class VaccinationCenterFormComponent {
  @Input() center?: VaccinationCenter;
  @Output() send = new EventEmitter<VaccinationCenter>();
  @Output() edit = new EventEmitter<VaccinationCenter>();

  centers?: VaccinationCenter[];
 
  userInfo = this.formBuilder.group({
    nom: [this.center?.name, [Validators.required]],
    address: [this.center?.address, [Validators.required]],
    city: [this.center?.city, [Validators.required]],
  })

  submitted = false;
  full = false;
  
  
  constructor(private formBuilder: FormBuilder, private service : VaccinationService) {
  }  

  ngOnInit(): void {
    this.service.getAllVaccinationCenter("").subscribe(resultCenters => {
      this.centers = resultCenters;
      this.userInfo = this.formBuilder.group({
        nom: [this.center?.name, [Validators.required]],
        address: [this.center?.address, [Validators.required]],
        city: [this.center?.city, [Validators.required]],
      })
    });
    
  }

  onSubmit() {
    this.submitted = true;

    if (this.userInfo.invalid) {
      this.full = true;
    }else{    
      if(this.center !== undefined){
        let center: VaccinationCenter = {
          name: this.userInfo.value.nom!,
          address: this.userInfo.value.address!,
          city: this.userInfo.value.city!,
          idCentre: this.center.idCentre,
          users: this.center.users,
          patients: this.center.patients
        }
        this.edit.emit(center);
      }
      else{
        let center: VaccinationCenter = {
          name: this.userInfo.value.nom!,
          address: this.userInfo.value.address!,
          city: this.userInfo.value.city!,
          idCentre: 0,
          users: [],
          patients: []
        }
        this.send.emit(center);
      }
    }

    setTimeout(() =>{ 
      this.full = false; 
    }, 3500);
    
  }

  
}

