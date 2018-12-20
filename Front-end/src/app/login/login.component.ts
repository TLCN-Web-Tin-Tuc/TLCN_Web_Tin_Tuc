import { Component, OnInit } from '@angular/core';
import { LoginService } from '../_service/login.service';
import { Guest } from '../_entity/guest';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { NuServiceService } from '../_service/nu_service/nu-service.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  guest : Guest  
  error : String
  
  constructor(private nuService : NuServiceService, private route : Router) { 
    this.guest = new Guest();
  }

  ngOnInit() {
    if(localStorage.email){
      this.route.navigate(["/"]);
    }

  }

  login()  {
    this.nuService.login(this.guest)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          localStorage.setItem("email", res.data.email)
          localStorage.setItem("lastName", res.data.lastName)       
          localStorage.setItem("password", res.data.password)       
          this.route.navigate(["/"]);
        }
        else
        {
            this.error = res.message
        }
      }, err => {
        console.log(err)
      })      
  }
  logout(){
    localStorage.clear()
  }
}
