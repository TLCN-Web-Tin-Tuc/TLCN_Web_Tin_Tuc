import { Component, OnInit } from '@angular/core';
import { AdminService } from '../_service/admin_service/admin.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Role } from '../_entity/role';

@Component({
  selector: 'app-role-edit',
  templateUrl: './role-edit.component.html',
  styleUrls: ['./role-edit.component.css']
})
export class RoleEditComponent implements OnInit {

  role : Role
  id : string
  ischeck : boolean
  email : string
  constructor( private activatedRoute: ActivatedRoute, private route : Router, private adminService : AdminService) { 
    this.role = new Role()
  }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(queryParams => {
      this.id = queryParams.get("id");      
    });
    this.retrieveRoleById(this.id)
  }

  retrieveRoleById(id){
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
    this.adminService.updateRole(this.role, this.email)    
          .subscribe(res => {
            if(res.success == "true"){
              alert("Update thành công")
              this.route.navigate(["/rolesmanagement"])
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


}
