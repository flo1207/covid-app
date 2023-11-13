import { Component, Input } from '@angular/core';
import { User } from '../User';
import { GestionService } from '../gestion.service';
import { ActivatedRoute } from '@angular/router';
import { UserForm } from '../user-form';
import { HttpErrorResponse } from '@angular/common/http';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {

  displayedColumns: string[] = ['idUser', 'prenom', 'nom','actions'];
  
  @Input() disp_super?: Boolean;
  @Input() disp_admin?: Boolean;
  @Input() disp_mdc?: Boolean;
  @Input() current_user!: User;
  @Input() current_id_center!: number;

  addUser = false;

  users?: User[];
  edit_user?: User;
  
  medecins?: User[];
  admins?: User[];
  supers?: User[];
  
  selected?: User;

  medecin = "ROLE_MDC"
  admin = "ROLE_ADMIN"
  super = "ROLE_SUPER"

  message = "Utilisateur créé avec succès!";
  confirm?: Boolean;
  error?: Boolean;

  constructor(private service: GestionService, private route: ActivatedRoute){ }
  
  ngOnInit(): void {
    var curr_id = null;
    if(this.current_id_center != null) curr_id = this.current_id_center
    else if(this.current_user.center != null) curr_id = this.current_user.center.idCentre
    if(!this.disp_super && curr_id != null){
      this.service.getAllUsers(curr_id!).subscribe(resultUsers => {
        this.users = resultUsers.filter(t=>t.role.authority === this.current_user.role.authority);
        this.medecins = resultUsers.filter(t=>t.role.authority === this.medecin);
        this.admins = resultUsers.filter(t=>t.role.authority === this.admin);
        this.supers = resultUsers.filter(t=>t.role.authority === this.super);
      });
    }
    //si super admin on va chercher juste la liste de super users
    else this.service.getAllSuper().subscribe(data => {
      this.supers = data.filter(t=>t.role.authority === this.super);
    })
    
   
  }

  getUserList(){
    var curr_id = null;
    if(this.current_id_center != null) curr_id = this.current_id_center
    else if(this.current_user.center != null) curr_id = this.current_user.center.idCentre
    if(!this.disp_super && curr_id != null){
      this.service.getAllUsers(curr_id!).subscribe(resultUsers => {
        this.users = resultUsers.filter(t=>t.role.authority === this.current_user.role.authority);
        this.medecins = resultUsers.filter(t=>t.role.authority === this.medecin);
        this.admins = resultUsers.filter(t=>t.role.authority === this.admin);
        this.supers = resultUsers.filter(t=>t.role.authority === this.super);
      });
    }
    else this.service.getAllSuper().subscribe(data => {
      this.supers = data.filter(t=>t.role.authority === this.super);
    })
  }

  onDeleted(event: User) {
    if(event.role.authority === this.admin){
      this.admins!.splice(this.admins!.indexOf(event),1)
    }
    else if(event.role.authority === this.medecin){
      this.medecins!.splice(this.medecins!.indexOf(event),1)
    }else if(event.role.authority === this.super){
      this.supers!.splice(this.supers!.indexOf(event),1)
    }
  }

  onEdit(event: User){
    this.addUser = true;
    this.edit_user = event;
  }

  back(){
    this.addUser = false;
    this.edit_user = undefined;

  }

  ajouterUser(){
    this.addUser = true;
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

  async editUser(event: UserForm){
    const send = (await this.service.editUser(event,this.edit_user!.idUser)).subscribe((response) => {
      this.confirm = true;
      this.message = "Le centre a bien été mis à jour.";
      this.getUserList();
      this.confirm = true;
      this.edit_user = undefined;
    },
    (error: HttpErrorResponse) => {
      this.message = "Une erreur est survenue, "+error.message;
      this.error = true;
    });

    this.addUser = false;
    setTimeout(() =>{ 
        this.confirm = false; 
    }, 3000);
  
    }
    



}
