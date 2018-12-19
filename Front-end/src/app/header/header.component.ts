import { Component, OnInit } from '@angular/core';
import { HeaderService } from '../_service/header/header.service';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { UserService } from '../_service/user_service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username : string
  email : string
  avatar : string
  error : String
  constructor(private userService  : UserService, private route : Router) { 
   
  }


  ngOnInit() {
    this.username = localStorage.getItem("lastName");
    this.email = localStorage.getItem("email")
    this.avatar = localStorage.getItem("avatar");
    this.findUser();

  }
  findUser()  {
    this.userService.getProfile(this.email)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          this.username = res.data.lastName
          if(this.avatar != null)
          this.avatar = res.data.avatar
          else
          this.avatar = "/assets/robust-admin/profile.png"
        }
        else
        {
            this.error = res.message
        }
      }, err => {
        // console.log(err)
      })      
  }

}
