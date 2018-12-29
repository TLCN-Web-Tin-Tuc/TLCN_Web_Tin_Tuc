import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../_service/user_service/user.service';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { User } from '../_entity/user';
import { first } from 'rxjs/operators';
import { Role } from '../_entity/role';
import { Cat } from '../_entity/cat';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  user: User
  catList: Cat[];
  email: string
  error: string
  isAdmin: boolean = false
  isMod: boolean = false
  isCreate: boolean = false
  roles: Role[]
  constructor(private router: Router, private modService: ModServiceService, private userService: UserService, private nuService: NuServiceService) {
    this.user = new User()
  }

  ngOnInit() {
    this.email = localStorage.getItem("email")

    this.userService.getProfile(this.email)
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
          this.user = res.data;
          this.roles = this.user.roles
          for (let role of this.roles) {
            if (role.p_admin == true && role.status == 1) {
              this.isAdmin = true;
            }
            if (role.p_delete == true || role.p_update == true || role.p_approve == true) {
              if (role.status == 1) {
                this.isMod = true
              }

            }
            if (role.p_create == true && role.status == 1) {
              this.isCreate = true
            }
          }
        }
        else {
          this.error = res.message
        }
      }, err => {
        console.log(err)
      })

      this.getCatList();
  }

  getCatList() {
    this.nuService.getAllCatChecked()
      .subscribe(res => {
        this.catList = res.data;
        if (res.success == "true") {
          this.catList = res.data;
        }
      }, err => {
        console.log(err.message)
      });
  }
}
