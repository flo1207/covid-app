import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VaccinationCenterComponent } from './Vaccination/vaccination-center/vaccination-center.component';
import { VaccinationCenterListComponent } from './Vaccination/vaccination-center-list/vaccination-center-list.component';
import { LoginPageComponent } from './login/login-page/login-page.component';
import { LoginPageGuard } from './guards/login-page.guard';
import { UserListComponent } from './gestion/user-list/user-list.component';
import { GestionPageGuard } from './guards/gestion-page.guard';
import { GestionTableComponent } from './gestion/gestion-table/gestion-table.component';

const routes: Routes = [
  {path: "centers",                   
    component: VaccinationCenterListComponent},
  {path: "",                            
    redirectTo:"/centers", 
    pathMatch:'full'},
  {path: "centers/users/:id",          
    component: UserListComponent},
  { path: 'admin', 
    component: LoginPageComponent,
    canActivate: [LoginPageGuard], 
  },
  { path: 'admin/gestion', 
    component: GestionTableComponent,
    canActivate: [GestionPageGuard]
  }


]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

