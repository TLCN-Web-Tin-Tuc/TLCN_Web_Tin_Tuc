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

  constructor(private adminService : AdminService, private fb: FormBuilder,private router : Router) {
    this.role = new Role();
   
   }

  ngOnInit() {
    
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
    
   
  
  
}
