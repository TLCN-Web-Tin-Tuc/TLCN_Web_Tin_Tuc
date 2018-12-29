import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';


import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { AdminService } from '../_service/admin_service/admin.service';
import { Router } from '@angular/router';
import { User } from '../_entity/user';
import { Role } from '../_entity/role';
import { UserService } from '../_service/user_service/user.service';
import { first } from 'rxjs/operators';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { Cat } from '../_entity/cat';

@Component({
  selector: 'app-role-management',
  templateUrl: './role-management.component.html',
  styleUrls: ['./role-management.component.css']
})
export class RoleManagementComponent implements OnInit {

  roles : Role[];
  rolesofUser : Role[]
  cats : Cat[]
  dataTable: any;
  email : string
  error : string;
  pass : string

  isAdmin : boolean = false
  constructor(private userService : UserService, private router : Router,private adminService : AdminService , 
    private chRef: ChangeDetectorRef, private nuService : NuServiceService) { }
  ngOnInit(){
    this.getAllRole()
    }
    async getAllRole(){
      await this.getAllCat()
      this.adminService.getAllRole()
      .subscribe(res => {
        if(res.success == "true")
        {
          
          this.roles = res.data;
          for(let role of this.roles)
          {
            if(role.status == 1){
              role.isCheck = true
            }
            else{
              role.isCheck = false
            }
            for(let cat of this.cats){
              if(cat.id == role.catId){
                role.catName = cat.name                
              }
            }

          }
          
          
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

    onGotoRoleEdit(id) {
      // this.router.navigate(["/editrole"], { queryParams: { id: id } });
      window.location.href = "/editrole/" + id;
    }

    toggleRoleCreate(e){
      this.email = localStorage.getItem("email")
      this.adminService.updateRoleCreate(e.target.value, this.email)
      .subscribe(res => {
      if(res.success == "true")
      {
        console.log("update thành công")
      }
      else{
        console.log("update không thành công")
      }
    }, err => {
      console.log(err);
    });
    }

    toggleRoleAdmin(e){
      this.email = localStorage.getItem("email")
      this.adminService.updateRoleAdmin(e.target.value, this.email)
      .subscribe(res => {
      if(res.success == "true")
      {
        console.log("update thành công")
      }
      else{
        console.log("update không thành công")
      }
    }, err => {
      console.log(err);
    });
    }



    toggleRoleUpdate(e){
      this.email = localStorage.getItem("email")
      this.adminService.updateRoleUpdate(e.target.value, this.email)
      .subscribe(res => {
      if(res.success == "true")
      {
        console.log("update thành công")
      }
      else{
        console.log("update không thành công")
      }
    }, err => {
      console.log(err);
    });
    }

    toggleRoleDelete(e){
      this.email = localStorage.getItem("email")
      this.adminService.updateRoleDelete(e.target.value, this.email)
      .subscribe(res => {
      if(res.success == "true")
      {
        console.log("update thành công")
      }
      else{
        console.log("update không thành công")
      }
    }, err => {
      console.log(err);
    });
    }

    toggleRoleApprove(e){
      this.email = localStorage.getItem("email")
      this.adminService.updateRoleApprove(e.target.value, this.email)
      .subscribe(res => {
      if(res.success == "true")
      {
        console.log("update thành công")
      }
      else{
        console.log("update không thành công")
      }
    }, err => {
      console.log(err);
    });
    }
    toggleRoleStatus(e){
      this.email = localStorage.getItem("email")
      this.adminService.updateStatusRole(e.target.value, this.email)
      .subscribe(res => {
      if(res.success == "true")
      {
        console.log("update thành công")
      }
      else{
        console.log("update không thành công")
      }
    }, err => {
      console.log(err);
    });
    }

  
  }
  
