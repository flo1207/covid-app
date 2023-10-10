import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UserForm } from '../user-form';
import { FormBuilder, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { VaccinationCenter } from 'src/app/Vaccination/vaccination-center';
import { VaccinationService } from 'src/app/Vaccination/vaccination.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit{
  
  @Output() send = new EventEmitter<UserForm>();
  
  centers?: VaccinationCenter[];
 
  userInfo = this.formBuilder.group({
    prenom: ['', [Validators.required]],
    nom: ['', [Validators.required]],
    mail: ['', [Validators.required]],
    password: ['', [Validators.required]],
    centre: [null, [Validators.required]],
    role: [null, [Validators.required]],
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
      let user: UserForm = {
        prenom: this.userInfo.value.prenom!,
        nom: this.userInfo.value.nom!,
        email: this.userInfo.value.mail!,
        password: this.userInfo.value.password!,
        id_center: this.userInfo.value.centre!,
        role: this.userInfo.value.role!
      }

      console.log(user)
      this.send.emit(user);

    }

    setTimeout(() =>{ 
      this.full = false; 
    }, 3500);
    
  }

  
}

