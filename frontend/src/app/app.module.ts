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
import { UserComponent } from './gestion/user/user.component';
import { UserListComponent } from './gestion/user-list/user-list.component';
import { GestionTableComponent } from './gestion/gestion-table/gestion-table.component';
import { PatientComponent } from './patient/patient/patient.component';


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
    PatientComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NgxBootstrapIconsModule.pick(allIcons)

  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
