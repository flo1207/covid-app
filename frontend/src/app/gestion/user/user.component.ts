import { Component, Input } from '@angular/core';
import { GestionService } from '../gestion.service';
import { User } from '../User';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {
  @Input() user?: User;
     
  constructor(private service: GestionService){ }
  
  // ngOnInit(): void {
  //   this.service.getUserById(3).subscribe(get_user => {
  //     this.user = get_user;
  //     console.log(get_user)
  //   });
  // }


}
