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

  async login(login: { username: string; password: string }) {
      
      const body = new HttpParams()
      .set('username', login.username)
      .set('password', login.password)
          const loginResponse = await firstValueFrom(
            this.httpClient.post<{ token: string }>("/api/public/login",body
            )
          )
      
          const tok = btoa(`${login.username}:${login.password}`);

          localStorage.setItem(LoginService.USERNAME_KEY,login.username);
          localStorage.setItem(LoginService.TOKEN_KEY,tok);
          this.token = loginResponse.token;
  }


  async logout() {
    localStorage.clear();
    window.location.reload();
  }


  getUsername(): Observable<string | null> {
    return this.username.asObservable();
  }
  
  getToken(){
    return localStorage.getItem("token");
  }
}
