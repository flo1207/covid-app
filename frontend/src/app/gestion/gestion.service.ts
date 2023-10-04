import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class GestionService {

  constructor(private httpClient : HttpClient) { }

  getAllUsers(id: string): Observable<User[]> {
    return this.httpClient.get<User[]>("api/public/centers/users/"+id); 
  }

  getUserById(id: string) {
    return this.httpClient.get<User>("api/users/"+id); 
  }
}
