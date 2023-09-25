import { Component } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';

@Component({
  selector: 'app-vaccination-center-list',
  templateUrl: './vaccination-center-list.component.html',
  styleUrls: ['./vaccination-center-list.component.scss']
})
export class VaccinationCenterListComponent {

  centers: VaccinationCenter[] = [
    {
      id: 1,
      name:"Hopital du centre",
      address: "2 rue des mimo",
      postalCode: "54000",
      city: "Nancy",
      openingDate:new Date(2012, 10,12)
    },{
      id: 2,
      name:"Hopital de l'est",
      address: "5 rue des compte",
      postalCode: "54000",
      city: "Nancy",
      openingDate:new Date(2009, 11,17)
    },{
      id: 3,
      name:"Hopital du centre",
      address: "8 rue des gorgo",
      postalCode: "57000",
      city: "Metz",
      openingDate:new Date(2012, 10,17)
    }
    
  ];

  selected?: VaccinationCenter;

  centerSelect(aCenter: VaccinationCenter) {
    this.selected = aCenter;
  }

  clearCenter() {
    this.selected = undefined;
  }

}
