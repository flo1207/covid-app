import { Component } from '@angular/core';
import { LoginService } from './login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'covid-api';

  constructor(private loginService: LoginService, private route : Router){}

  isConnected(){
    return this.loginService.getToken() != null
  }

  isAdmin(){
    return (this.route.url == "/admin" || this.route.url == "/admin/gestion")
  }

  logout(){
    this.loginService.logout();
  }

}
