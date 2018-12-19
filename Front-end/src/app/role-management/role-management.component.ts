import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';


import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { AdminService } from '../_service/admin_service/admin.service';
import { Router } from '@angular/router';
import { User } from '../_entity/user';
import { Role } from '../_entity/role';

@Component({
  selector: 'app-role-management',
  templateUrl: './role-management.component.html',
  styleUrls: ['./role-management.component.css']
})
export class RoleManagementComponent implements OnInit {

  roles : Role[];
  dataTable: any;

  constructor(private router : Router,private adminService : AdminService , private chRef: ChangeDetectorRef) { }
  ngOnInit(){
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

          }
          console.log(this.roles)
          
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
  
    onGotoRoleEdit(id) {
      this.router.navigate(["/editrole"], { queryParams: { id: id } });
    }

    toggleRoleCreate(e){
      this.adminService.updateRoleCreate(e.target.value)
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
      this.adminService.updateRoleUpdate(e.target.value)
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
      this.adminService.updateRoleDelete(e.target.value)
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
      this.adminService.updateRoleApprove(e.target.value)
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
      this.adminService.updateStatusRole(e.target.value)
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
  
