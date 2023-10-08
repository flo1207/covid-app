import { Component } from '@angular/core';
import { VaccinationCenter } from 'src/app/Vaccination/vaccination-center';
import { VaccinationService } from 'src/app/Vaccination/vaccination.service';
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
  id_center?: number;
  title: string = "Médecins(s)"
  role: number = 1;

  user! : User;

  center?: VaccinationCenter;

  patients?: Patient[];

  constructor(private service: GestionService, private logservice: LoginService){}

  async ngOnInit() { 
    this.service.getUserByUsername(localStorage.getItem('username')!).subscribe(data => {
      this.user = data;
      this.center = this.user.center;
      this.patients = this.center.patients
      this.id_center = this.center.idCentre
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

}
