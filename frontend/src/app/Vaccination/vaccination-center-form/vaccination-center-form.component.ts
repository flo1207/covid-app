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
  
  @Output() send = new EventEmitter<VaccinationCenter>();
  
  centers?: VaccinationCenter[];
 
  userInfo = this.formBuilder.group({
    nom: ['', [Validators.required]],
    address: ['', [Validators.required]],
    city: ['', [Validators.required]],
  })

  submitted = false;
  full = false;
  
  
  constructor(private formBuilder: FormBuilder, private service : VaccinationService) {
  }  

  ngOnInit(): void {
    this.service.getAllVaccinationCenter("").subscribe(resultCenters => {
      this.centers = resultCenters;
    });
  }

  onSubmit() {
    this.submitted = true;

    if (this.userInfo.invalid) {
      this.full = true;
    }else{    
      let center: VaccinationCenter = {
        name: this.userInfo.value.nom!,
        address: this.userInfo.value.address!,
        city: this.userInfo.value.city!,
        idCentre: 0,
        users: [],
        patients: []
      }

      console.log(center)
      this.send.emit(center);

    }

    setTimeout(() =>{ 
      this.full = false; 
    }, 3500);
    
  }

  
}

