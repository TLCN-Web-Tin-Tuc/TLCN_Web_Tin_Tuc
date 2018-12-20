import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';


import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { AdminService } from '../_service/admin_service/admin.service';
import { Router } from '@angular/router';
import { User } from '../_entity/user';
import { UserService } from '../_service/user_service/user.service';
import { Role } from '../_entity/role';
import { first } from 'rxjs/operators';
@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  // Our array of clients
  users : User[];
  dataTable: any;
  rolesofUser : Role[]
  email : string
  error : string;
  isAdmin : boolean = false
constructor(private router : Router,private adminService : AdminService , private chRef: ChangeDetectorRef, private userService : UserService) {
  
 }

ngOnInit(){
  this.getListUser()
  }

  async getListUser(){
    await this.checkEmail()
    this.adminService.getListUser()
    .subscribe(res => {
      if(res.success == "true")
      {
        
        this.users = res.data;
        
      }
     

      // You'll have to wait that changeDetection occurs and projects data into 
      // the HTML template, you can ask Angular to that for you ;-)
      this.chRef.detectChanges();

      // Now you can use jQuery DataTables :
      const table: any = $('table');
      this.dataTable = table.DataTable();
    }, err => {
      console.log(err.message)
  });
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

  onGotoUserDetail(id) {
    this.router.navigate(["/profile"], { queryParams: { id: id } });
  }

}
