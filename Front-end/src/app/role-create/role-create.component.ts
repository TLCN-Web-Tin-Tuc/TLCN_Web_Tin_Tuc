import { Component, OnInit } from '@angular/core';
import { User } from '../_entity/user';
import { Router } from '@angular/router';
import { UserService } from '../_service/user_service/user.service';
import { first } from 'rxjs/operators';
import { FormBuilder ,FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { Role } from '../_entity/role';
import { AdminService } from '../_service/admin_service/admin.service';

@Component({
  selector: 'app-role-create',
  templateUrl: './role-create.component.html',
  styleUrls: ['./role-create.component.css']
})
export class RoleCreateComponent implements OnInit {
   user: User;
  error: string;
  email: string;   
  role : Role;
  rolesofUser : Role[]
  isAdmin : boolean = false

  constructor(private adminService : AdminService, private fb: FormBuilder,private router : Router, private userService : UserService) {
    this.role = new Role();
   
   }

  ngOnInit() {
    this.checkEmail()
  }
  createRole(){   
      this.role.status = 1
      this.email = localStorage.getItem("email")
      this.adminService.createRole(this.role, this.email).pipe(first()).subscribe(res => {       
        if(res.success == "true"){
          alert("Tạo quyền thành công !!");         
          this.router.navigate(["/rolesmanagement"]);         
          this.error = "";
        }
        else{
          alert("Tạo quyền không thành công!!!");
        } 
        
       
    },
    err => {
        this.error = err.message
    })
  }

  checkEmail(){
    this.email = localStorage.getItem("email")
    if(this.email == null)
    {
      this.router.navigate(["/"])
    }
    this.userService.getProfile(this.email)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "true")
      {                     
        this.rolesofUser = res.data.roles
        for(let role of this.rolesofUser)
        {
          if(role.rname == "ROLE_ADMIN")
          {
            this.isAdmin = true;
            return
          }           
        }
        if(this.isAdmin == false){
          alert("Bạn không được truy cập vào trang này")
          this.router.navigate(["/"])
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
