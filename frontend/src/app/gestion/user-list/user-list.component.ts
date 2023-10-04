import { Component } from '@angular/core';
import { User } from '../User';
import { GestionService } from '../gestion.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {
  users?: User[];
  
  selected?: User;

  id = this.route.snapshot.paramMap.get('id');

  constructor(private service: GestionService, private route: ActivatedRoute){ }
  
  ngOnInit(): void {
    this.service.getAllUsers(this.id!).subscribe(resultUsers => {
      this.users = resultUsers;
    });

    this.users?.sort((a,b) => a.role - b.role);

  }


}
