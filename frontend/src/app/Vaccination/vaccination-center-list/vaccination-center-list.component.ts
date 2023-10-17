import { Component, EventEmitter, Input, OnInit, Output, PipeTransform } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { VaccinationService } from '../vaccination.service';
import { Observable, map, startWith } from 'rxjs';
import { DecimalPipe } from '@angular/common';
import { FormControl } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-vaccination-center-list',
  templateUrl: './vaccination-center-list.component.html',
  styleUrls: ['./vaccination-center-list.component.scss'],
  providers: [DecimalPipe],
})
export class VaccinationCenterListComponent implements OnInit{

  @Input() gestion?: boolean;
  @Output() edituser = new EventEmitter<VaccinationCenter>();

  centers?: VaccinationCenter[];

  current_center?: VaccinationCenter;

  centers$: Observable<VaccinationCenter[]>

	filter = new FormControl('', { nonNullable: true });

  selected?: VaccinationCenter;

  city : string = "";

  addCenter = false;
  message = "Centre créé avec succès!";
  confirm?: Boolean;
  error?: Boolean;


  


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
    this.filter.setValue("")
  }

  async createCenter(center: VaccinationCenter) {
    const send = (await this.service.postCenter(center)).subscribe((response) => {
        this.confirm = true;
        this.service.getAllVaccinationCenter(this.city).subscribe(resultCenters => {
          this.centers = resultCenters;
          this.filter.setValue("")
        });
    },
    (error: HttpErrorResponse) => {
      this.message = "Une erreur est survenue, "+error.message;
      this.error = true;
      console.log(error.status)
    });

    this.ajouterCenter()
    setTimeout(() =>{ 
        this.confirm = false; 
      }, 3000);
    
  }

  ajouterCenter(){
    this.addCenter = !this.addCenter;
  }

  onEdit(event: VaccinationCenter){
    this.addCenter = true;
    this.current_center = event;
  }

  onEditUser(event: VaccinationCenter){
    this.edituser.emit(event)
  }

  async editCenter(event: VaccinationCenter){
    const send = (await this.service.editCenter(event)).subscribe((response) => {
      this.confirm = true;
      this.service.getAllVaccinationCenter(this.city).subscribe(resultCenters => {
        this.message = "Le centre a bien été mis à jour.";
        this.centers = resultCenters;
        this.filter.setValue("")
        this.current_center = undefined;
      });
    },
    (error: HttpErrorResponse) => {
      this.message = "Une erreur est survenue, "+error.message;
      this.error = true;
    });

    this.ajouterCenter()
    setTimeout(() =>{ 
        this.confirm = false; 
    }, 3000);
  
  }

  back(){
    this.addCenter = false;
  }


}
