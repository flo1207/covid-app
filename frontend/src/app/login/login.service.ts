import { Injectable } from '@angular/core';
import { BehaviorSubject, firstValueFrom, Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { VaccinationCenter } from '../Vaccination/vaccination-center';
import { User } from '../gestion/User';
import { GestionService } from '../gestion/gestion.service';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  static USERNAME_KEY = 'username';
  static TOKEN_KEY = 'token';

  private username = new BehaviorSubject<string | null>(null);
  private token: string | null = null;

  public user! : User

  constructor(private httpClient: HttpClient, private gestionService: GestionService) {
    
  }

  // async login(login: { username: string; password: string }) {
  //   const loginResponse = await firstValueFrom(
  //     this.httpClient.post<{ token: string }>("/auth/login",
  //       {
  //         username: login.username,
  //         password: login.password,
  //       }
  //     )
  //   );
  //   localStorage.setItem(LoginService.USERNAME_KEY,login.username);
  //   localStorage.setItem(LoginService.TOKEN_KEY,loginResponse.token);
  //   this.token = loginResponse.token;
  //   this.username.next(login.username);
  // }

  async login(login: { username: string; password: string }) {

      const body = new HttpParams()
      .set('username', login.username)
      .set('password', login.password)
    
      const resp = await this.httpClient.post("api/public/login", body, {
        headers: new HttpHeaders()
          .set('Content-Type', 'application/x-www-form-urlencoded'),
        observe: 'response'
      })
      
      localStorage.setItem(LoginService.USERNAME_KEY,login.username);
      localStorage.setItem(LoginService.TOKEN_KEY,"valid");
      this.token = "valid";     
      return resp;

  }


  async logout() {
    localStorage.clear();
    window.location.reload();
  }


  // async logout() {
  //   if (this.token) {
  //     try {
  //       await firstValueFrom(
  //         this.httpClient.post("/auth/logout", {})
  //       );
  //     } catch (error) {
  //       console.log('Could not logout.');
  //     }

    
  //   localStorage.removeItem(LoginService.USERNAME_KEY);
  //   localStorage.removeItem(LoginService.TOKEN_KEY);
  //   this.username.next(null);
  //   this.token = null;
  //   }
  // }

  getUsername(): Observable<string | null> {
    return this.username.asObservable();
  }
  
  getToken(){
    return localStorage.getItem("token");
  }
}
