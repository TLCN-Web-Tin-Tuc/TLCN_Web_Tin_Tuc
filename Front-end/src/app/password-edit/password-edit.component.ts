import { Component, OnInit } from '@angular/core';
import { UserService } from '../_service/user_service/user.service';
import { first } from 'rxjs/operators';
import { User } from '../_entity/user';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-password-edit',
  templateUrl: './password-edit.component.html',
  styleUrls: ['./password-edit.component.css']
})
export class PasswordEditComponent implements OnInit {

  email: string = "";
  oldPassword: string = "";
  newPassword: string = "";
  conFirmNewPassword: string = "";
  pass : string = ""
  selectedImg : string = ""
  user : User

  error : string = ""
  constructor(private userService : UserService, private nuService : NuServiceService, private route :Router) { }

  ngOnInit() {
    this.getProfile()
  }

  async getProfile(){
    await this.checkUserAndPassWord()
    this.email = localStorage.getItem("email")
    this.userService.getProfile(this.email)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "true")
      {
        this.user = res.data;
        this.selectedImg = this.user.avatar                    
      }
      else
      {
          this.error = res.message
      }
    }, err => {
      console.log(err)
    })  
  }

  
  checkUserAndPassWord(){
    this.email = localStorage.getItem("email")
    this.pass = localStorage.getItem("password")
    this.nuService.checkEmailPass(this.email, this.pass)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "false")
      {            
        localStorage.clear()
        // this.route.navigate(["/"]);
        window.location.href = "/";
      }
      
    }, err => {
      console.log(err)
    })      
  }


  doiMatKhau(){
    this.email = localStorage.getItem("email");
    if(this.newPassword == this.conFirmNewPassword)
    {
      this.userService.doiMatKhau(this.email, this.oldPassword, this.newPassword).pipe(first())
      .subscribe(res =>{
          if(res.success == "true")
            alert("Đổi mật khẩu thành công !!!");
      },
      err =>{
          this.error = err.message
      });
    }
    else{
      this.error = "Mật khẩu nhập lại không khớp"
    }
  } 
    
}
