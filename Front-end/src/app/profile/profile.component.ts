import { Component, OnInit } from '@angular/core';
import { User } from '../_entity/user';
import { UserService } from '../_service/user_service/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { Role } from '../_entity/role';
import { AdminService } from '../_service/admin_service/admin.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user : User
  error : string
  email : string

  id : string
  selectedImg : string = ""
  admin : string = "false"
  kichhoat : string
  isChecked: boolean = false;
  rolesOfUser: Role[];
  roles: Role[];

  constructor(private userService : UserService, private activatedRoute: ActivatedRoute, private route : Router, private adminService : AdminService) { 
    this.user = new User();
  }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(queryParams => {
      this.id = queryParams.get("id");      
    });
    this.retrieveUserById(this.id);
    //this.getAllRoles(this.id);
    console.log(this.id)


  }

   retrieveUserById(id) {
    if(this.id)
    {
      this.adminService.findUserById(this.id)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          this.user = res.data;   
          this.selectedImg = this.user.avatar    
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
      this.userService.getProfile(this.email)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          this.user = res.data;
          this.selectedImg = this.user.avatar
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

  onSetStatus(){
    this.adminService.setStatus(this.id)
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
        alert("update thành công")
      }
      else{
        alert("update không thành công")
      }
    }, err => {
      console.log(err);
    });
  }

}