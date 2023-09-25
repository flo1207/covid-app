import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VaccinationCenterComponent } from './vaccination-center/vaccination-center.component';
import { VaccinationCenterListComponent } from './vaccination-center-list/vaccination-center-list.component';

const routes: Routes = [
  {path: "centers",                     component: VaccinationCenterListComponent},
  {path: "",                            redirectTo:"/centers", pathMatch:'full'},
  {path: "centers/detail/:id",          component: VaccinationCenterComponent},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

