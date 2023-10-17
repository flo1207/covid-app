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
  @Input() disp_super?: Boolean;
  @Input() disp_admin?: Boolean;
  @Input() disp_mdc?: Boolean;

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
      this.users = resultUsers.filter(t=>t.role.authority === this.role);
      this.medecins = resultUsers.filter(t=>t.role.authority === this.medecin);
      this.admins = resultUsers.filter(t=>t.role.authority === this.admin);
      if(!this.disp_super) this.supers = resultUsers.filter(t=>t.role.authority === this.super);
      else this.service.getAllSuper().subscribe(data => {
        this.supers = data.filter(t=>t.role.authority === this.super);
      })
    });
  }

  getUserList(){
    this.service.getAllUsers(this.id!).subscribe(resultUsers => {
      this.users = resultUsers.filter(t=>t.role.authority === this.role);
      this.medecins = resultUsers.filter(t=>t.role.authority === this.medecin);
      this.admins = resultUsers.filter(t=>t.role.authority === this.admin);
      if(!this.disp_super) this.supers = resultUsers.filter(t=>t.role.authority === this.super);
      else this.service.getAllSuper().subscribe(data => {
        this.supers = data.filter(t=>t.role.authority === this.super);
      })
    });
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
    this.current_user = event;
  }

  back(){
    this.addUser = false;
    this.current_user = undefined;

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
    const send = (await this.service.editUser(event,this.current_user!.idUser)).subscribe((response) => {
      this.confirm = true;
      this.message = "Le centre a bien été mis à jour.";
      this.getUserList();
      this.confirm = true;
      this.current_user = undefined;
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
