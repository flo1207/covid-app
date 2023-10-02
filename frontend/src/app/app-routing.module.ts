import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VaccinationCenterComponent } from './Vaccination/vaccination-center/vaccination-center.component';
import { VaccinationCenterListComponent } from './Vaccination/vaccination-center-list/vaccination-center-list.component';
import { LoginPageComponent } from './login/login-page/login-page.component';
import { LoginPageGuard } from './guards/login-page.guard';
import { ChatPageGuard } from './guards/chat-page.guard';

const routes: Routes = [
  {path: "centers",                     component: VaccinationCenterListComponent},
  {path: "",                            redirectTo:"/centers", pathMatch:'full'},
  {path: "centers/detail/:id",          component: VaccinationCenterComponent},
  { path: 'admin', 
    component: LoginPageComponent,
    canActivate: [LoginPageGuard], 
  },
  { path: 'chat', 
    component: VaccinationCenterComponent,
    canActivate: [ChatPageGuard]
  },



]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

