import { Component, OnInit, ChangeDetectorRef } from '@angular/core';


import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { Router } from '@angular/router';
import { Item } from '../_entity/item';
import { Role } from '../_entity/role';
import { first } from 'rxjs/operators';
import { UserService } from '../_service/user_service/user.service';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { Cat } from '../_entity/cat';
import { CatItem } from '../_entity/catitem'

@Component({
  selector: 'app-cat-new-management',
  templateUrl: './cat-new-management.component.html',
  styleUrls: ['./cat-new-management.component.css']
})
export class CatNewManagementComponent implements OnInit {

    // Our array of clients
    items: Item[];
    dataTable: any;
    //catOfUser : Cat[]
    email : string
    isMod : boolean = false
    rolesofUser : Role[]
    cats : Cat[]
    catItems : CatItem[]
    select : number
    catId : number    
    pass : string
    error : string

  constructor(private router : Router,private modService: ModServiceService, private chRef: ChangeDetectorRef,
    private userService : UserService, private nuService : NuServiceService) { }

  ngOnInit(){
   
    this.getListItem()
  }

  async getListItem(){
    await  this.getAllItem()
    await this.checkEmail()        
  }
  getAllItem(){
    
    this.nuService.getItemDescDayAll()
    .subscribe(res => {
      if(res.success == "true")
      {
        
        this.items = res.data;        
        
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
  onGotoItemDetail(id) {

    // this.router.navigate(["/newsdetail/" + id]);
    window.location.href = "/newsdetail/" + id;
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
        console.log(this.rolesofUser)
        for(let role of this.rolesofUser)
        {
          if(role.p_update == true || role.p_admin == true )
          {
            if(role.status == 1){
              this.isMod = true;
              return
            }
            
          }         
         
        }
        if(this.isMod == false){
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
