import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../_service/user_service/user.service';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { User } from '../_entity/user';
import { first } from 'rxjs/operators';
import { Role } from '../_entity/role';
import { Cat } from '../_entity/cat';
import { parentCat } from '../_entity/parentcat';

@Component({
  selector: 'app-cat-menu',
  templateUrl: './cat-menu.component.html',
  styleUrls: ['./cat-menu.component.css']
})
export class CatMenuComponent implements OnInit {

  user: User
  catList: parentCat[];
  email: string
  error: string
  isDashboard: boolean = false
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
            if (role.rname == "ROLE_ADMIN" && role.status == 1) {
              this.isAdmin = true;
            }
            if (role.p_delete == true || role.p_update == true || role.p_approve == true) {
              if (role.status == 1) {
                this.isMod = true;
              }

            }
            if (role.p_create == true && role.status == 1) {
              this.isCreate = true;
            }
            if (this.isAdmin == true || this.isMod == true || this.isCreate == true) {
              this.isDashboard = true;
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
    this.nuService.getAllParentCatChecked()
      .subscribe(res => {
        if (res.success == "true") {
          this.catList = res.data;

          for (let i = 0; i < this.catList.length; i++) {
            this.nuService.getChildCat(this.catList[i].id)
              .subscribe(res => {

                if (res.success == "true") {
                  this.catList[i].childCat = res.data;
                  this.catList[i].isNullChildCat = this.catList[i].childCat.length;
                }
              }, err => {
                console.log(err.message)
              });
          }
        }
      }, err => {
        console.log(err.message)
      });
  }
}

