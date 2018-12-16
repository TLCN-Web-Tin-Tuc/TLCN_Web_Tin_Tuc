import { Component, OnInit } from '@angular/core';
import { UserService } from '../_service/user_service/user.service';
import { first } from 'rxjs/operators';

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
  error : string = ""
  constructor(private userService : UserService) { }

  ngOnInit() {
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
