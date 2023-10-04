import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { trigger,transition,style,animate } from '@angular/animations';
import { AppComponent } from 'src/app/app.component';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  constructor(
    private router: Router,
    private loginService: LoginService,
    private appComponent: AppComponent) {
  }

  ngOnInit(): void {}


  
  async onLogin(login: { username: string; password: string }) {
    try{
      console.log("ici")
      await this.loginService.login(login)
      this.router.navigate(['admin/gestion']);
    }catch(err: any){
      if (err instanceof HttpErrorResponse) {
        if(err.status == 403) window.alert("Mot de passe invalide.");
        else window.alert("Problème de connexion.");
      } 
      else {
        window.alert("Un problème est survenu.");
      }
    }
  }
}
