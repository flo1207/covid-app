import { Component, Input } from '@angular/core';
import { User } from '../User';
import { GestionService } from '../gestion.service';
import { ActivatedRoute } from '@angular/router';
import { UserForm } from '../user-form';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {
  users?: User[];
  
  current_user?: User;

  medecins?: User[];

  admins?: User[];

  supers?: User[];
  
  selected?: User;

  @Input() id?: number;
  @Input() role?: string;

  addUser = false;

  medecin = "ROLE_MDC"
  admin = "ROLE_ADMIN"
  super = "ROLE_SUPER"

  message = "Utilisateur créé avec succès!";
  confirm?: Boolean;
  error?: Boolean;


  // id = this.route.snapshot.paramMap.get('id');

  constructor(private service: GestionService, private route: ActivatedRoute){ }
  
  ngOnInit(): void {
    this.service.getAllUsers(this.id!).subscribe(resultUsers => {
      console.log(resultUsers)
      this.users = resultUsers.filter(t=>t.role.authority === this.role);
      this.medecins = resultUsers.filter(t=>t.role.authority === this.medecin);
      this.admins = resultUsers.filter(t=>t.role.authority === this.admin);
      this.supers = resultUsers.filter(t=>t.role.authority === this.super);
    });
  }

  getUserList(){
    this.service.getAllUsers(this.id!).subscribe(resultUsers => {
      this.users = resultUsers.filter(t=>t.role.authority === this.role);
      this.medecins = resultUsers.filter(t=>t.role.authority === this.medecin);
      this.admins = resultUsers.filter(t=>t.role.authority === this.admin);
      this.supers = resultUsers.filter(t=>t.role.authority === this.super);
    });
  }

  isSuper(){
    return this.role == this.super;
  }

  onDeleted(event: User) {
    this.users!.splice(this.users!.indexOf(event),1)
    this.medecins!.splice(this.medecins!.indexOf(event),1)
    this.admins!.splice(this.medecins!.indexOf(event),1)
  }

  onEdit(event: User){
    this.addUser = true;
    this.current_user = event;
  }

  modifierUser(){
    this.addUser = true;
    console.log("modifier user")
  }

  back(){
    this.addUser = false;
    this.current_user = undefined;

  }

  ajouterAdmin(){
    this.addUser = true;
    console.log("ajout admin")
  }

  ajouterMdc(){
    this.addUser = true;
    console.log("ajout mdc")
  }

  async createUser(user: UserForm) {
    const send = (await this.service.postUser(user)).subscribe((response) => {
        this.getUserList();
        this.confirm = true;

    },
    (error: HttpErrorResponse) => {
      this.message = "Une erreur est survenue, "+error.message;
      this.error = true;
      console.log(error.status)
    });

    this.addUser = false;
    setTimeout(() =>{ 
        this.confirm = false; 
      }, 3000);
    
  }


}
