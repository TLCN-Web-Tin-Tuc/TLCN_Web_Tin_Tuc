import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';


import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { AdminService } from '../_service/admin_service/admin.service';
import { Router } from '@angular/router';
import { User } from '../_entity/user';
@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  // Our array of clients
  users : User[];
  dataTable: any;

constructor(private router : Router,private adminService : AdminService , private chRef: ChangeDetectorRef) {
  
 }

ngOnInit(){
  alert("Có vào trongnày")
  this.adminService.getListUser()
    .subscribe(res => {
      alert("Có vào hàm này")
      if(res.success == "true")
      {
        alert("Lấy dữ liệu thành công")
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

}
