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
          localStorage.setItem(LoginService.TOKEN_KEY,loginResponse.token);
          this.token = loginResponse.token;
  }


  async logout() {
    window.location.reload();
    localStorage.clear();
  }


  getUsername(): Observable<string | null> {
    return this.username.asObservable();
  }

  // on verifie que le token ne soit pas expiré
  private tokenExpired(token: string) {
    const [header, payload, signature] = token.split('.');
    // Decode the payload
    const decodedPayload = JSON.parse(atob(payload));
    // Check the expiration date  
    if (decodedPayload && decodedPayload.exp) {
      const expirationDate = new Date(0);
      expirationDate.setUTCSeconds(decodedPayload.exp);

      // Compare with the current date
      if (expirationDate < new Date()) {
        return false;
      } else {
        return true;
      } 
    } else {
    return false;
  } 
    
  }
  
  getToken(){
    if(localStorage.getItem("token") != null && !this.tokenExpired(localStorage.getItem("token")!)) localStorage.clear();
    return localStorage.getItem("token");
  }
}
