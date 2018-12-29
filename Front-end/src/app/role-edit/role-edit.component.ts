import { Component, OnInit } from '@angular/core';
import { AdminService } from '../_service/admin_service/admin.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Role } from '../_entity/role';
import { UserService } from '../_service/user_service/user.service';
import { first } from 'rxjs/operators';
import { Cat } from '../_entity/cat';

@Component({
  selector: 'app-role-edit',
  templateUrl: './role-edit.component.html',
  styleUrls: ['./role-edit.component.css']
})
export class RoleEditComponent implements OnInit {

  role : Role
  id : number;
  ischeck : boolean
  email : string
  rolesofUser : Role[]
  cats : Cat[]  
  error : string
  isAdmin : boolean = false
  constructor( private activatedRoute: ActivatedRoute, private route : Router, private adminService : AdminService, private userService : UserService) { 
    this.role = new Role()
  }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(queryParams => {
      this.id = +this.activatedRoute.snapshot.paramMap.get('id');      
    });
    this.retrieveRoleById(this.id)
  }

  async retrieveRoleById(id){
    await this.getAllCat()
    this.adminService.findRoleById(this.id)    
          .subscribe(res => {
            this.role = res.data;             
            if(this.role.status == 0) {
              this.ischeck = false
            } 
            else{
              this.ischeck = true
            }
           // console.log(this.rolesOfUser);    
           //console.log(this.roles); 
          }, err => {
            console.log(err);
          });
  }

  UpdateRole(){
    if(this.ischeck == false) {
      this.role.status = 0
    } 
    else{
      this.role.status = 1
    }
    this.email = localStorage.getItem("email")
    this.adminService.updateRole(this.role,this.email)    
          .subscribe(res => {
            if(res.success == "true"){
              alert("Update thành công")
              // this.route.navigate(["/rolesmanagement"])
              window.location.href = "/rolesmanagement";  
            }
            else{
              alert("Update thất bại")
            }
                          
           // console.log(this.rolesOfUser);    
           //console.log(this.roles); 
          }, err => {
            console.log(err);
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

  checkEmail(){
    this.email = localStorage.getItem("email")
    if(this.email == null)
    {
      // this.route.navigate(["/"])
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
          // this.route.navigate(["/"])
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
    

}
