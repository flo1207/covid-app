import { Component, OnInit } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { VaccinationService } from '../vaccination.service';

@Component({
  selector: 'app-vaccination-center-list',
  templateUrl: './vaccination-center-list.component.html',
  styleUrls: ['./vaccination-center-list.component.scss']
})
export class VaccinationCenterListComponent implements OnInit{

  centers?: VaccinationCenter[];

  selected?: VaccinationCenter;

  constructor(private service: VaccinationService){ }
  
  ngOnInit(): void {
    this.service.getAllVaccinationCenter("").subscribe(resultCenters => {
      this.centers = resultCenters;
    });
  }

  centerSelect(aCenter: VaccinationCenter) {
    this.selected = aCenter;
  }

  clearCenter() {
    delete this.selected;
  }

  onDeleted(event: VaccinationCenter) {
    delete this.selected;
    this.centers!.splice(this.centers!.indexOf(event),1)
  }

}
