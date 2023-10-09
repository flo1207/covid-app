import { Component, OnInit, PipeTransform } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { VaccinationService } from '../vaccination.service';
import { Observable, map, startWith } from 'rxjs';
import { DecimalPipe } from '@angular/common';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-vaccination-center-list',
  templateUrl: './vaccination-center-list.component.html',
  styleUrls: ['./vaccination-center-list.component.scss'],
  providers: [DecimalPipe],
})
export class VaccinationCenterListComponent implements OnInit{

  centers?: VaccinationCenter[];
  centers$: Observable<VaccinationCenter[]>

	filter = new FormControl('', { nonNullable: true });

  selected?: VaccinationCenter;

  city : string = "";


  constructor(private service: VaccinationService, pipe: DecimalPipe){ 
    this.centers$ = this.filter.valueChanges.pipe(
			startWith(''),
			map((text) => this.search(text, pipe)),
		);
  }
  
  search(text: string, pipe: PipeTransform): VaccinationCenter[] {
    return this.centers!.filter((center) => {
      const term = text.toLowerCase();
      return (
        center.name.toLowerCase().includes(term) ||
        center.city.toLowerCase().includes(term) ||
        center.address.toLowerCase().includes(term)
      );
    });
  }
  
  ngOnInit(): void {
    this.service.getAllVaccinationCenter(this.city).subscribe(resultCenters => {
      this.centers = resultCenters;
    });
  }

  centerSelect(aCenter: VaccinationCenter) {
    this.selected = aCenter;
  }

  getCenter(name: string){
    this.service.getAllVaccinationCenter(this.city).subscribe(resultCenters => {
      this.centers = resultCenters;
    });
  }

  clearCenter() {
    delete this.selected;
  }

  onDeleted(event: VaccinationCenter) {
    delete this.selected;
    this.centers!.splice(this.centers!.indexOf(event),1)
  }

}
