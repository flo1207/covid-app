import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './User';
import { UserForm } from './user-form';

@Injectable({
  providedIn: 'root'
})
export class GestionService {
  dellUser(idUser: number) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Basic ${token}`,
      observe: 'response'
    });

    const body = new HttpParams()
    .set('idUser', idUser)

    return this.httpClient.delete("api/private/user/"+idUser,{headers}); 
  }
  

  getUserByUsername(username: string){
    const token = localStorage.getItem('token');
    
    const headers = new HttpHeaders({
      Authorization: `Basic ${token}`,
    });


    return this.httpClient.get<User>("api/private/get/user",{headers}); 
  }

  constructor(private httpClient : HttpClient) { }

  getAllUsers(id: number): Observable<User[]> {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Basic ${token}`,
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


  postUser(user: UserForm) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Basic ${token}`,
      observe: 'response'
    });


    const body = new HttpParams()
    .set('nom', user.nom)
    .set('prenom', user.prenom)
    .set('password', user.password)
    .set('mail', user.email)
    .set('id_center', user.id_center)
    .set('role', user.role)

    
    return this.httpClient.post<User>("api/private/users",body,{headers}); 
  }
}
