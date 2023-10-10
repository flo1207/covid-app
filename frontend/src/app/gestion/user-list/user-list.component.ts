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

  supprimerUser(user: User){
    console.log("supprimer user")
  }

  modifierUser(){
    this.addUser = true;
    console.log("modifier user")
  }

  back(){
    this.addUser = false;
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
        console.log(response);
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
