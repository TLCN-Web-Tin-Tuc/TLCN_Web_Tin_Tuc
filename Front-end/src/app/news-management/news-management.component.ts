import { HttpClient } from '@angular/common/http';
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
import { CatItem } from '../_entity/catitem';

@Component({
  selector: 'app-news-management',
  templateUrl: './news-management.component.html',
  styleUrls: ['./news-management.component.css']
})
export class NewsManagementComponent implements OnInit {

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
    isAdmin : boolean = false

  constructor(private router : Router,private modService: ModServiceService, private chRef: ChangeDetectorRef,
    private userService : UserService, private nuService : NuServiceService) { }

  ngOnInit(){
   
    this.checkEmail()
  }

  async getListItem(){
    await  this.getCatOfUser()
          
  }
  async getCatOfUser(){
    await this.checkEmail() 
    console.log(this.isAdmin)
   
   
  }
  onGotoItemDetail(id) {

    // this.router.navigate(["/newsdetail/" + id]);
    window.location.href = "/newsdetail/" + id;
  }

  onChangeCatSelect(e){
    this.modService.getItemByCatId(this.select)
    .subscribe(res => {
      
      
      if(res.success == "true")
      {

      
        $('table').dataTable().fnDestroy();
        
        this.catItems = res.data;
       
        
      }
      this.chRef.detectChanges();
      const table: any = $('table');
      this.dataTable = table.DataTable();
  
       }, err => {
        console.log("err.message")
        $('table').dataTable().fnClearTable();
    });
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
          if(role.p_admin == true && role.status == 1){
            this.isAdmin = true           
          }
          if(role.p_delete == true || role.p_approve == true )
          {
            if(role.status == 1){
              this.isMod = true;             
            }
            
          }           
        }
        if(this.isAdmin == false && this.isMod == false){
         
            alert("Bạn không được truy cập vào trang này")
            // this.router.navigate(["/"])
            window.location.href = "/"; 
         
        }else{

          if(this.isAdmin == true){ 
            this.modService.getAllCat()
            .subscribe(res => {
              if(res.success == "true")
              {
                
                this.cats = res.data;
                this.catId = this.cats[0].id
                
              }
              this.select = this.catId
              this.modService.getItemByCatId(this.catId)
            .subscribe(res => {
              if(res.success == "true")
              {
                
                this.catItems = res.data;
                
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
              
               }, err => {
                console.log(err.message)
            });
      
          } else{
            this.email = localStorage.getItem("email")
            this.modService.getAllCatofUser(this.email)
            .subscribe(res => {
              if(res.success == "true")
              {
                
                this.cats = res.data;
                this.catId = this.cats[0].id
                
              }
              this.select = this.catId
              this.modService.getItemByCatId(this.catId)
            .subscribe(res => {
              if(res.success == "true")
              {
                
                this.catItems = res.data;
                
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
              
               }, err => {
                console.log(err.message)
            });
          }


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
