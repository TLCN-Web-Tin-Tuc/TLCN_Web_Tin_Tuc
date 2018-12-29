import { Component, OnInit } from '@angular/core';
import { User } from '../_entity/user';
import { Router } from '@angular/router';
import { UserService } from '../_service/user_service/user.service';
import { first } from 'rxjs/operators';
import { FormBuilder ,FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { Role } from '../_entity/role';
import { AdminService } from '../_service/admin_service/admin.service';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { Cat } from '../_entity/cat';

@Component({
  selector: 'app-role-create',
  templateUrl: './role-create.component.html',
  styleUrls: ['./role-create.component.css']
})
export class RoleCreateComponent implements OnInit {
   user: User;
  error: string;
  email: string;   
  selectCat : number = 0;
  pass : string
  role : Role;
  cats : Cat[]
  rolesofUser : Role[]
  isAdmin : boolean = false

  constructor(private adminService : AdminService, private fb: FormBuilder,private router : Router, private userService : UserService, private nuService :NuServiceService) {
    this.role = new Role();
   
   }

  ngOnInit() {
    this.getAllCat()
  }
  createRole(){   
      this.role.status = 1
      this.email = localStorage.getItem("email")
      this.adminService.createRole(this.role, this.email).pipe(first()).subscribe(res => {       
        if(res.success == "true"){
          alert("Tạo quyền thành công !!");         
          // this.router.navigate(["/rolesmanagement"]);    
          window.location.href = "/rolesmanagement";     
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

  async getAllCat(){
    await this.checkEmail()
    this.adminService.getAllCat()
    .pipe(first())
    .subscribe(res => {
      if(res.success == "true")
      {            
        this.cats = res.data
        console.log(this.cats)
      }else{
        console.log(res.message)
      }
      
    }, err => {
      console.log(err)
    })      
    
  }

  async checkEmail(){
    await this.checkUserAndPassWord()
    this.email = localStorage.getItem("email")
    if(this.email == null)
    {
      // this.router.navigate(["/"])
      window.location.href = "/";  
    }
    this.userService.getProfile(this.email)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "true")
      {                     
        this.rolesofUser = res.data.roles
        for(let role of this.rolesofUser)
        {
          if(role.p_admin == true && role.status == 1)
          {
            this.isAdmin = true;
            return
          }           
        }
        if(this.isAdmin == false){
          alert("Bạn không được truy cập vào trang này")
          // this.router.navigate(["/"])
          window.location.href = "/";  
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
    
  checkUserAndPassWord(){
    this.email = localStorage.getItem("email")
    this.pass = localStorage.getItem("password")
    this.nuService.checkEmailPass(this.email, this.pass)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "false")
      {            
        localStorage.clear()
        // this.router.navigate(["/"]);
        window.location.href = "/";  
      }
      
    }, err => {
      console.log(err)
    })      
  }
  
  
}
