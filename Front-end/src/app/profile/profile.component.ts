import { Component, OnInit } from '@angular/core';
import { User } from '../_entity/user';
import { UserService } from '../_service/user_service/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { Role } from '../_entity/role';
import { AdminService } from '../_service/admin_service/admin.service';
import { LoginService } from '../_service/login.service';
import { NuServiceService } from '../_service/nu_service/nu-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user : User
  error : string
  email : string
  pass : string
  id : number
  selectedImg : string = ""
  admin : string = "false"
  kichhoat : string
  isChecked: boolean = false;
  rolesOfUser: Role[];
  roles: Role[];
  rolesUser : Role[];
  isAdmin: boolean = false
  constructor(private userService : UserService, private activatedRoute: ActivatedRoute, 
    private route : Router, private adminService : AdminService, private nuService : NuServiceService) { 
    this.user = new User();
  }

  ngOnInit() {
    this.checkUserAndPassWord()
    this.activatedRoute.queryParamMap.subscribe(queryParams => {
      this.id = +this.activatedRoute.snapshot.paramMap.get('id');         
    });
    this.retrieveUserById(this.id);
    //this.getAllRoles(this.id);
    console.log(this.id)


  }

    retrieveUserById(id) {
      
    if(this.id!=-1)
    {
      this.checkEmail()
      this.adminService.findUserById(this.id)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          this.user = res.data;
          if(this.user.avatar == null){                   
            this.selectedImg = "/assets/robust-admin/profile.png"
          } else 
          {
            this.selectedImg = this.user.avatar
          }   
    
          this.admin = "true" 
          this.rolesOfUser = this.user.roles    


          this.adminService.getAllRole()
          .subscribe(res => {
            this.roles = res.data;  
            for(let role of this.roles){
              role.isOfUser = false
            }

            if (this.rolesOfUser) {
              for (let role of this.roles) {            
                if (this.rolesOfUser.find(x => x.rname == role.rname)) {
                  role.isOfUser = true;
                }
              }
            }
           // console.log(this.rolesOfUser);    
           //console.log(this.roles); 
          }, err => {
            console.log(err);
          });


          if(this.user.status == 1)
          {
            this.kichhoat = "Hủy kích hoạt"
          } 
          else{
            this.kichhoat = "Kích hoạt"
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
    else{
      this.email = localStorage.getItem("email")
      this.checkUserAndPassWord();
      this.userService.getProfile(this.email)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          this.user = res.data;

          if(this.user.avatar == null){                   
            this.selectedImg = "/assets/robust-admin/profile.png"
          } else 
          {
            this.selectedImg = this.user.avatar
          }
          
          localStorage.setItem("lastName",this.user.lastName.toString());
          this.admin = "false"
          this.rolesOfUser = res.data.roles
          
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

  checkUserAndPassWord(){
    this.email = localStorage.getItem("email")
    this.pass = localStorage.getItem("password")
    this.nuService.checkEmailPass(this.email, this.pass)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "false")
      {            
        localStorage.clear()
        // this.route.navigate(["/"]);
        window.location.href = "/";
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
        this.rolesUser = res.data.roles
        for(let role of this.rolesUser)
        {
          if(role.rname == "ROLE_ADMIN" && role.status == 1)
          {
            this.isAdmin = true;
            return
          }           
        }
        if(this.isAdmin == false){         
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

  onSetStatus(){
    this.email = localStorage.getItem("email")
    this.adminService.setStatus(this.id, this.email)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {          
          this.user = res.data;   
          this.selectedImg = this.user.avatar    
          this.admin = "true" 
          if(this.user.status == 1)
          {
            this.kichhoat = "Hủy kích hoạt"
          } 
          else{
            this.kichhoat = "Kích hoạt"
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

  // Quyền của user
  async getAllRoles(id) {   
    await this.retrieveUserById(id)
    this.adminService.getAllRole()
      .subscribe(res => {
        this.roles = res.data;  
        console.log(this.rolesOfUser);        
        if (this.rolesOfUser) {
          for (let role of this.rolesOfUser) {            
            if (this.roles.find(x => x.rname == role.rname)) {
              role.isOfUser = true;
            }
          }
        }
      }, err => {
        console.log(err);
      });
  }

  toggleRoleOfUser(e) {
    this.isChecked = e.target.checked;
    console.log(this.isChecked);
    console.log(this.id)
    console.log(e.target.value)
    this.adminService.updateUserRole(this.id,e.target.value)
    .subscribe(res => {
      if(res.success == "true")
      {
        alert("Update thành công !!!")
      }
      else{
        alert("Update không thành công !!!")
      }
    }, err => {
      console.log(err);
    });
  }

}