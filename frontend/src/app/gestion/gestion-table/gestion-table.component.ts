import { Component } from '@angular/core';
import { VaccinationCenter } from 'src/app/Vaccination/vaccination-center';
import { LoginService } from 'src/app/login/login.service';
import { Patient } from 'src/app/patient/patient';
import { User } from '../User';
import { GestionService } from '../gestion.service';

@Component({
  selector: 'app-gestion-table',
  templateUrl: './gestion-table.component.html',
  styleUrls: ['./gestion-table.component.scss']
})
export class GestionTableComponent {
  id_center_user!: number;
  activeId: any;

  user! : User;
  center?: VaccinationCenter;
  patients?: Patient[];

  disp_super = false;
  disp_admin = false;
  disp_mdc = false;
  disp_user_center = false;

  constructor(private service: GestionService, private logservice: LoginService){}

  async ngOnInit() { 
    
    this.service.getUserByUsername().subscribe(data => {
      this.user = data;
      this.center = this.user.center;
      this.patients = this.center.patients
      this.setActive();
      this.disp_super = this.isSuper();
      this.disp_admin = this.isAdmin();
      this.disp_mdc = this.isMdc();

    },error => {
        if (error.status === 401) {
          // L'accès est non autorisé, gérer en conséquence
          console.error('Accès non autorisé:', error);
        } else {
          // Gérez d'autres erreurs
          console.error('Erreur de récupération de l\'utilisateur:', error);
        }
      }
    );    
  }

  logout(){
    this.logservice.logout()
  }

  isMdc(){
    return (this.user?.role.authority == "ROLE_MDC" || this.user?.role.authority == "ROLE_ADMIN" || this.user?.role.authority == "ROLE_SUPER")
  }

  isAdmin(){
    return (this.user?.role.authority == "ROLE_ADMIN" || this.user?.role.authority == "ROLE_SUPER")
  }

  isSuper(){
    return this.user?.role.authority == "ROLE_SUPER"
  }

  setActive(){
    if(this.isSuper()) this.activeId = "1";
    else if(this.isAdmin()) this.activeId = "2";
    else if(this.isMdc()) this.activeId = "3";
  }  

  onEditUser(event: VaccinationCenter){
    this.id_center_user = event.idCentre;
    this.disp_user_center = true;
  }

  back(){
    this.disp_user_center = false;
  }

}
