import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';



@Injectable({
  providedIn: 'root'
})
export class LoginPageGuard implements CanActivate {

  constructor(
    private router: Router,
    private loginService: LoginService) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.loginService.getToken() != null){
      this.router.navigate(['admin/centers']);
      return false;
    }
    return true;
  }
  
}
