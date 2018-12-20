import { Component, OnInit } from '@angular/core';
import { Cat } from '../_entity/cat';
import { Router } from '@angular/router';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { first } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { Role } from '../_entity/role';
import { UserService } from '../_service/user_service/user.service';

@Component({
  selector: 'app-cat-create',
  templateUrl: './cat-create.component.html',
  styleUrls: ['./cat-create.component.css']
})
export class CatCreateComponent implements OnInit {
  cat: Cat;
  sCat: Cat;
  sCatname: string;
  catList: Cat[];
  error: string;
  email: string;
  role: Role;
  rolesofUser: Role[];
  isMod: boolean = false;
  isCreate: boolean = false;
  selectedOption: number;

  constructor(private fb: FormBuilder, private router: Router, private modService: ModServiceService, private userService: UserService) {
    this.cat = new Cat();
    this.sCat = new Cat();
  }

  ngOnInit() {
    this.checkEmail();
    this.getCatList();
  }

  createSCat() {
    this.sCat.name = this.sCatname;
    this.sCat.parent_id = this.selectedOption;
    this.sCat.dateCreated = new Date();
    this.sCat.userCreated = localStorage.getItem("email");
    this.email = localStorage.getItem("email")
    this.modService.createCat(this.sCat).pipe(first()).subscribe(res => {
      if (res.success == "true") {
        alert("Tạo danh mục thành công !!!");       
        this.error = "";
      }
      else {
        alert("Tạo danh mục không thành công !!!");
      }
      this.getCatList();
    },
      err => {
        this.error = err.message
      })
  }

  createCat() {
    this.email = localStorage.getItem("email")
    this.cat.dateCreated = new Date();
    this.cat.userCreated = localStorage.getItem("email");
    this.email = localStorage.getItem("email")
    this.modService.createCat(this.cat).pipe(first()).subscribe(res => {
      if (res.success == "true") {
        alert("Tạo danh mục thành công !!!");        
        this.error = "";
      }
      else {
        alert("Tạo danh mục không thành công !!!");
      }
      this.getCatList();
    },
      err => {
        this.error = err.message
      })
  }

  getCatList() {
    this.modService.getAllCat()
      .subscribe(res => {
        this.catList = res.data;
        console.log(this.catList)
      }, err => {
        console.log(err.message)
      });
  }

  checkEmail() {
    this.email = localStorage.getItem("email")
    if (this.email == null) {
      this.router.navigate(["/"])
    }
    this.userService.getProfile(this.email)
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
          this.rolesofUser = res.data.roles
          for (let role of this.rolesofUser) {
            if ((role.p_delete == true || role.p_update == true || role.p_approve == true) && (role.status == 1)) {
              this.isMod = true
            }
            if (role.p_create == true && (role.status == 1)) {
              this.isCreate = true
            }
          }
          if (this.isMod == false) {
            alert("Bạn không được truy cập vào trang này")
            this.router.navigate(["/"])
          }
        }
        else {
          this.error = res.message
        }
      }, err => {
        console.log(err)
      })
  }

  changeSelected(){
    alert(this.selectedOption)
  }
}
