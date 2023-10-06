import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class GestionService {
  get(username: string){
    return this.httpClient.get<User>("api/login/user",{
      params:{
        "username":username
      }
    }); 
  }

  constructor(private httpClient : HttpClient) { }

  getAllUsers(id: number): Observable<User[]> {
    return this.httpClient.get<User[]>("api/public/center/users/"+id); 
  }

  getUserById(id: string) {
    return this.httpClient.get<User>("api/users/"+id); 
  }

  getUser(username: string, password: string){
    return this.httpClient.get<User>("api/user",{
      params:{
        "username":username,
        "password": password
      }
    }); 
  }
}
