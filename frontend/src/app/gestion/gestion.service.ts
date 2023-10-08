import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class GestionService {

  getUserByUsername(username: string){
    const token = localStorage.getItem('token');
    
    const base64Credentials = btoa(`${username}:${token}`);
    const headers = new HttpHeaders({
      Authorization: `Basic ${base64Credentials}`,
    });


    return this.httpClient.get<User>("api/private/get/user",{headers}); 
  }

  constructor(private httpClient : HttpClient) { }

  getAllUsers(id: number): Observable<User[]> {
    const token = localStorage.getItem('token');
    const username = localStorage.getItem('username');

    const base64Credentials = btoa(`${username}:${token}`);
    const headers = new HttpHeaders({
      Authorization: `Basic ${base64Credentials}`,
    });
    return this.httpClient.get<User[]>("api/private/center/users/"+id,{headers}); 
  }

  getUserById(id: string) {
    return this.httpClient.get<User>("api/users/"+id); 
  }

  getUser(username: string, password: string){
    return this.httpClient.get<User>("api/puser",{
      params:{
        "username":username,
        "password": password
      }
    }); 
  }
}
