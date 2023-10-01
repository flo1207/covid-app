import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { VaccinationCenterComponent } from './vaccination-center/vaccination-center.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { VaccinationCenterListComponent } from './vaccination-center-list/vaccination-center-list.component';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { PatientComponent } from './patient/patient.component';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    VaccinationCenterComponent,
    VaccinationCenterListComponent,
    PatientFormComponent,
    PatientComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
