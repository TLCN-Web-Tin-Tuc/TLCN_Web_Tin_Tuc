import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { first } from 'rxjs/operators';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from '../_entity/item';
import { Role } from '../_entity/role';
import { User } from '../_entity/user';
import { UserService } from '../_service/user_service/user.service';
import { Cat } from '../_entity/cat';
import * as $ from 'jquery';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { CatItem } from '../_entity/catitem';

@Component({
  selector: 'app-news-detail',
  templateUrl: './news-detail.component.html',
  styleUrls: ['./news-detail.component.css',
    '../../assets/magz-master/css/style.css',
    '../../assets/magz-master/css/skins/all.css',
    '../../assets/magz-master/scripts/sweetalert/dist/sweetalert.css',
    '../../assets/magz-master/scripts/magnific-popup/dist/magnific-popup.css',
    '../../assets/magz-master/scripts/owlcarousel/dist/assets/owl.theme.default.min.css',
    '../../assets/magz-master/scripts/owlcarousel/dist/assets/owl.carousel.min.css',
    '../../assets/magz-master/scripts/toast/jquery.toast.min.css',
    '../../assets/magz-master/scripts/ionicons/css/ionicons.min.css'
  ]
})
export class NewsDetailComponent implements OnInit {

  item: Item
  error: string
  email: string
  id: string
  selectedImg: string = ""
  mod: string = "false"
  kichhoat: string
  isChecked: boolean = false;
  role: Role;
  rolesofUser: Role[];
  isMod: boolean = false;
  isCreate: boolean = false;
  isDelete: string = "false";
  cat: Cat;
  catOfItem : Cat[]
  catList: Cat[];
  dataTable: any;
  catItem : CatItem[]

  constructor(private modService: ModServiceService, private activatedRoute: ActivatedRoute,
     private route: Router, private userService: UserService, private chRef: ChangeDetectorRef, private nuService : NuServiceService) {
    this.item = new Item();
  }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(queryParams => {
      this.id = queryParams.get("id");
    });
    this.retrieveItemById(this.id);
    console.log(this.id)
  }

  retrieveItemById(id) {
    if (this.id) {
       this.checkEmail();
      this.modService.findItemById(this.id)
        .pipe(first())
        .subscribe(res => {
          if (res.success == "true") {

            this.item = res.data;
            this.mod = "true"
            this.selectedImg = this.item.image;

            if (this.item.status == 1) {
              this.kichhoat = "Bỏ duyệt bài";
            }
            else {
              this.kichhoat = "Duyệt bài";
            }

            if (this.item.status != 2) {
              this.isDelete = "true";
            }

            
            if (this.item.status == 2) {
              this.isDelete = "false";
            }

            this.modService.getAllCat()
              .subscribe(res => {
                this.catList = res.data;
                if (res.success == "true") {

                  this.catList = res.data;                  
                  this.chRef.detectChanges();
                  
                  // // Now you can use jQuery DataTables :
                  // const table: any = $('table');
                  // this.dataTable = table.DataTable();
                  this.nuService.getCatItem(this.id)
                  .subscribe(res => {
                    if(res.success == "true"){                      
                      for (let cat of this.catList) {
                        cat.isOfItem = false;
                      }
                      this.catItem = res.data                     
                      if (this.catList) {
                        for (let cat of this.catList) {            
                          if (this.catItem.find(x => x.catName  == cat.name)) {
                            cat.isOfItem = true;
                          }
                        }
                      }

                    }
                  },err =>{
                      console.log("Loi cmnr")
                  })
                  

                }
              }, err => {
                console.log(err.message)
              });
          }
          else {
            this.error = res.message;
          }
        }, err => {
          console.log(err);
        })
    }
  }

  checkEmail() {
    this.email = localStorage.getItem("email");
    if (this.email == null) {
      this.route.navigate(["/"])
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
              return
            }
          }
          if (this.isMod == false) {
            alert("Bạn không được truy cập vào trang này")
            this.route.navigate(["/"])
          }
        }
        else {
          this.error = res.message
        }
      }, err => {
        console.log(err)
      })
  }

  onSetStatus(){
    this.email = localStorage.getItem("email")
    this.item.userUpdated = this.email;
    this.modService.updateItem(this.id, this.email)
      .pipe(first())
      .subscribe(res => {
        this.retrieveItemById(this.id);
      }, err => {
        console.log(err)
      })  
  }
  onSetDelete(){
    this.email = localStorage.getItem("email")
    this.item.userUpdated = this.email;
    this.modService.deleteItem(this.id, this.email)
      .pipe(first())
      .subscribe(res => {
        this.route.navigate(["/newsmanagement"])
      }, err => {
        console.log(err)
      })  
  }

  togglecat(e){
    if(e.target.checked == true){
      this.modService.addCatOnItem(this.id, e.target.value)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true"){
          console.log("Update thanh cong")
        }        
      }, err => {
        console.log(err)
      })  
    }else{
      this.modService.DeleteCatOnItem(this.id, e.target.value)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true"){
          console.log("Delete thanh cong")
        }        
      }, err => {
        console.log(err)
      })  
    }
  }

}