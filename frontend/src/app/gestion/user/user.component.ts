import { Component, EventEmitter, Input, Output } from '@angular/core';
import { GestionService } from '../gestion.service';
import { User } from '../User';
import { BehaviorSubject } from 'rxjs';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: '[app-user]',

  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {
  @Input() user?: User;
  @Output() deleted = new EventEmitter<User>;
  @Output() edited = new EventEmitter<User>;

  conf = false;
     
  constructor(private service: GestionService){ }
  
  // ngOnInit(): void {
  //   this.service.getUserById(3).subscribe(get_user => {
  //     this.user = get_user;
  //     console.log(get_user)
  //   });
  // }

  supprimerUser(){
    this.service.dellUser(this.user!.idUser).subscribe(() =>{
      this.deleted.emit(this.user);
    });
  }

  setConf(){
    this.conf = true;
    setTimeout(() =>{ 
      this.conf = false; 
    }, 3000);
  }

  modifierUser(){
    this.edited.emit(this.user);
  }


}
