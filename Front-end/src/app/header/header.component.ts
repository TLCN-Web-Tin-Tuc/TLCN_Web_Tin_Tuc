import { Component, OnInit } from '@angular/core';
import { HeaderService } from '../_service/header/header.service';
import { Router } from '@angular/router'
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
    //this.username = localStorage.getItem("lastName");
    //this.email = localStorage.getItem("email")
    this.findUser();

  }
  findUser()  {
    this.email = localStorage.getItem("email")    
    if(this.email !=null)
    {
      this.userService.getProfile(this.email)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          this.username = res.data.lastName
          this.avatar = res.data.avatar
          if(this.avatar == null){                   
            this.avatar = "/assets/robust-admin/profile.png"
          }
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
  onLogout(){
    localStorage.clear()
    // this.route.navigate(["/login"]);
    window.location.href = "/login";
  }

  gotoProfile(){
    window.location.href = "/profile/-1";
  }

  gotoChangePassword(){
    window.location.href = "/editpassword";
  }

}
