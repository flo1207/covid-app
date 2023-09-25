import { Component, Input } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';

@Component({
  selector: 'app-vaccination-center',
  templateUrl: './vaccination-center.component.html',
  styleUrls: ['./vaccination-center.component.scss']
})
export class VaccinationCenterComponent {
  
  @Input() center?: VaccinationCenter 
}
