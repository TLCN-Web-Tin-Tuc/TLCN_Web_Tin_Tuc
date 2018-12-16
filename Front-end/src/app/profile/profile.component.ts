import { Component, OnInit } from '@angular/core';
import { User } from '../_entity/user';
import { UserService } from '../_service/user_service/user.service';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

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
        localStorage.setItem("lastName",this.user.dateOfBirth.toString());
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
