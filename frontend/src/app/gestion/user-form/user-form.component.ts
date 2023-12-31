import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UserForm } from '../user-form';
import { FormBuilder, Validators } from '@angular/forms';
import { VaccinationCenter } from 'src/app/Vaccination/vaccination-center';
import { VaccinationService } from 'src/app/Vaccination/vaccination.service';
import { User } from '../user_detail';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit{
  
  @Input() user?: User;
  @Input() current_user!: User;

  @Output() send = new EventEmitter<UserForm>();
  @Output() edit = new EventEmitter<UserForm>();

  centers?: VaccinationCenter[];

  role?:String
 
  userInfo = this.formBuilder.group({
    prenom: [this.user?.prenom, [Validators.required]],
    nom: [this.user?.nom, [Validators.required]],
    mail: [this.user?.mail, [Validators.required]],
    password: ["", [Validators.required]],
    centre: [null, [Validators.required]],
    role: [null, [Validators.required]],
  })

  submitted = false;
  full = false;
  isSuper = false;
  
  
  constructor(private formBuilder: FormBuilder, private service : VaccinationService) {
  }  

  ngOnInit(): void {
    this.userInfo = this.formBuilder.group({
      prenom: [this.user?.prenom, [Validators.required]],
      nom: [this.user?.nom, [Validators.required]],
      mail: [this.user?.mail, [Validators.required]],
      password: ["", [Validators.required]],
      centre: [null, [Validators.required]],
      role: [null, [Validators.required]],
    })

    this.service.getAllVaccinationCenter("").subscribe(resultCenters => {
      this.centers = resultCenters;
      this.isSuper = this.current_user.role.authority == "ROLE_SUPER"
    });

    this.role = this.current_user.role.authority

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
      if(this.user){
        this.edit.emit(user);
      }else{
        this.send.emit(user);
      }
    }

    setTimeout(() =>{ 
      this.full = false; 
    }, 3500);
    
  }

}

