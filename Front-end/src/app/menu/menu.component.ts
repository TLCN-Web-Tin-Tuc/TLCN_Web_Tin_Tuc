import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../_service/user_service/user.service';
import { User } from '../_entity/user';
import { first } from 'rxjs/operators';
import { Role } from '../_entity/role';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  user : User
  email : string
  error : string
  isAdmin : boolean = false
  isMod : boolean = false
  isCreate : boolean = false
  roles : Role[]
  constructor(private router : Router,private userService : UserService ) {
    this.user = new User()
   }

  ngOnInit() {
    this.email = localStorage.getItem("email")
      this.userService.getProfile(this.email)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          this.user = res.data;                      
          this.roles = this.user.roles
          for(let role of this.roles)
          {
            if(role.rname == "ROLE_ADMIN")
            {
              this.isAdmin = true;
            }
            if(role.p_delete == true || role.p_update == true || role.p_approve == true)
            {
              this.isMod = true
            }
            if(role.p_create == true)
            {
              this.isCreate = true              
            }
          }

        }
        else
        {
            this.error = res.message
        }
      }, err => {
        console.log(err)
      })  

  }  


}
