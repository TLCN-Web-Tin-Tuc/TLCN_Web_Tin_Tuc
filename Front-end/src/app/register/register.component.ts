import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { User } from '../_entity/user';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user : User
  error : string
  status : string
  
  constructor(private router : Router,private nuService : NuServiceService ) {
    this.user = new User()
    this.user.sex = 1
    this.user.status = 1
   }

  ngOnInit() {
  }

  register(){
    if(this.user.password == this.user.confirmPassword)
    {
      this.nuService.register(this.user).pipe(first()).subscribe(res => {
        console.log(res.message)
        if(res.success == "true"){
          alert("Tạo tài khoản thành công !!");
          localStorage.setItem("email", res.data.email)
          localStorage.setItem("lastName", res.data.lastName)
          // this.router.navigate(["/"]);    
          window.location.href = "/";     
          this.error = "";
        }
        else{
          alert("Email đã tồn tại !!!");
        }
        
       
    },
    err => {
        this.error = err.message
    })
    }
    else{
      this.error = "Mật khẩu nhập lại không khớp";
    }
   
  }
  checkEmail(){
    this.nuService.checkEmail(this.user.email).pipe(first()).subscribe(res => {

    },
    err => {

    })
  }

}
