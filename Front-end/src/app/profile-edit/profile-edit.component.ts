import { Component, OnInit } from '@angular/core';
import { User } from '../_entity/user';
import { Router } from '@angular/router';
import { UserService } from '../_service/user_service/user.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {

  user : User
  error : string
  email : string

  constructor(private userService : UserService, private route : Router) {
    this.user = new User();
   }

  ngOnInit() {
    this.email = localStorage.getItem("email")
    this.userService.getProfile(this.email)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "true")
      {
        this.user = res.data;    
           
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
