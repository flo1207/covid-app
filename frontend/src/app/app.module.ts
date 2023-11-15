import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { VaccinationCenterComponent } from './Vaccination/vaccination-center/vaccination-center.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { VaccinationCenterListComponent } from './Vaccination/vaccination-center-list/vaccination-center-list.component';
import { PatientFormComponent } from './patient/patient-form/patient-form.component';
import { DatePipe } from '@angular/common';
import { LoginPageComponent } from './login/login-page/login-page.component';
import { LoginFormComponent } from './login/login-form/login-form.component';

import { NgxBootstrapIconsModule, allIcons} from 'ngx-bootstrap-icons';
import { UserComponent } from './gestion/user-display/user.component';
import { UserListComponent } from './gestion/user-list/user-list.component';
import { GestionTableComponent } from './gestion/gestion-table/gestion-table.component';
import { PatientListComponent } from './patient/patient-list/patient-list.component';
import { UserFormComponent } from './gestion/user-form/user-form.component';
import { VaccinationCenterFormComponent } from './Vaccination/vaccination-center-form/vaccination-center-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button'
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';


@NgModule({
  declarations: [
    AppComponent,
    VaccinationCenterComponent,
    VaccinationCenterListComponent,
    PatientFormComponent,
    LoginPageComponent,
    LoginFormComponent,
    UserComponent,
    UserListComponent,
    GestionTableComponent,
    PatientListComponent,
    UserFormComponent,
    VaccinationCenterFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NgxBootstrapIconsModule.pick(allIcons),
    BrowserAnimationsModule,
    MatButtonModule,
    MatTableModule,
    MatIconModule

  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
