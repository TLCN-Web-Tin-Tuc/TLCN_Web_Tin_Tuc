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
  id: number
  selectedImg: string = ""
  mod: string = "false"
  kichhoat: string
  isChecked: boolean = false;
  isLike : boolean = false;
  role: Role;
  rolesofUser: Role[];
  isMod: boolean = false;
  isUpdate: boolean = false;
  isDelete: boolean = false;
  isModDelete : boolean = false
  isApprove : boolean = false
  itemLast1 : Item
  itemLast2 : Item 
  itemLast3 : Item
  isItemNull : boolean = false
  itemLast4 : Item
  itemDescDay : Item[]
  cat: Cat;
  catOfItem : Cat[]
  catListItem: Cat[];
  dataTable: any;
  catItem : CatItem[]
  noneUser : boolean = false;

  constructor(private modService: ModServiceService, private activatedRoute: ActivatedRoute,
     private route: Router, private userService: UserService, private chRef: ChangeDetectorRef, private nuService : NuServiceService) {
    this.item = new Item();
    this.role = new Role()
    this.cat = new Cat()
    this.itemLast1  = new Item();
    this.itemLast2  = new Item();
    this.itemLast3  = new Item();
    this.itemLast4  = new Item();
  }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(queryParams => {
      this.id = +this.activatedRoute.snapshot.paramMap.get('id');
    });
    this.retrieveItemById(this.id);
    this.loadItemChinh();
  }
  checkLike(id){
    this.email = localStorage.getItem("email");
    if(this.email == null){
      this.noneUser = true
    }else{
      this.userService.checkLikeItem(id,this.email)
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
          this.isLike = true
        }
       
      }, err => {
        console.log(err)
      })
    }
    this.nuService.updateViewItem(id)
    .pipe(first())
    .subscribe(res => {
      if (res.success == "true") {
        console.log("Update view thành công")
      }
     
    }, err => {
      console.log(err)
    })
   
  }
 loadItemChinh(){  
  this.nuService.getItemDescDay()
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
          this.itemDescDay = res.data              
          if(this.itemDescDay.length < 4){
            this.isItemNull = true
          }
          this.itemLast1 = this.itemDescDay[1]                             
          this.itemLast2 = this.itemDescDay[2]          
          this.itemLast3 = this.itemDescDay[3]         
          this.itemLast4 = this.itemDescDay[4] 
          
        }
        else {
          
        }
      }, err => {
        console.log(err)
      })

  }

  likeItem(){
    this.userService.likeItem(this.id, this.email)
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
         console.log("Like Item thành công")
         this.isLike = true
         this.item.likes = this.item.likes + 1
        }
        else {
          console.log("Like Item thất bại")
        }
      }, err => {
        console.log(err)
      })

  }
  disLikeItem(){
    this.userService.disLikeItem(this.id, this.email)
    .pipe(first())
    .subscribe(res => {
      if (res.success == "true") {
       console.log("DisLike Item thành công")
       this.isLike = false
       this.item.likes = this.item.likes - 1
      }
      else {
        console.log("DisLike Item thất bại")
      }
    }, err => {
      console.log(err)
    })

  }

  async retrieveItemById(id) {
    await this.checkLike(id)
    if (this.id) {
       this.checkEmail();
      this.modService.findItemById(this.id)
        .pipe(first())
        .subscribe(res => {
          if (res.success == "true") {

            this.item = res.data;  
            console.log(this.item)          
            this.selectedImg = this.item.image;
            console.log(this.item.status)
            if (this.item.status == 1) {
              this.kichhoat = "Duyệt bài";
            }
            if (this.item.status == 2) {
              this.kichhoat = "Bỏ duyệt bài";
            }

            if (this.item.status != 3) {
              this.isDelete = true;
            }

            
            if (this.item.status == 3) {
              this.isDelete = false;
            }

            this.modService.getAllCat()
              .subscribe(res => {
                this.catListItem = res.data;
                if (res.success == "true") {

                  this.catListItem = res.data;                  
                  this.chRef.detectChanges();
                  
                  // // Now you can use jQuery DataTables :
                  // const table: any = $('table');
                  // this.dataTable = table.DataTable();
                  this.nuService.getCatItem(this.id)
                  .subscribe(res => {
                    if(res.success == "true"){                      
                      for (let cat of this.catListItem) {
                        cat.isOfItem = false;
                      }
                      this.catItem = res.data                     
                      if (this.catListItem) {
                        for (let cat of this.catListItem) {            
                          if (this.catItem.find(x => x.catName  == cat.name)) {
                            cat.isOfItem = true;
                          }
                        }
                      }
                      console.log(this.catListItem)
                    }
                  },err =>{
                      console.log("Loi ")
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
    
     this.userService.getProfile(this.email)
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
          this.rolesofUser = res.data.roles
          for (let role of this.rolesofUser) {           
            if (role.p_update == true && (role.status == 1)) {
              this.isUpdate = true              
            }
            if (role.p_admin == true && (role.status == 1)) {
              this.isUpdate = true              
              this.isModDelete = true
              this.isApprove = true
            }
          }    
          this.nuService.getCatItem(this.id)
          .pipe(first())
          .subscribe(res => {
             if (res.success == "true") {
                this.catItem = res.data
                console.log(this.catItem)
                console.log(this.rolesofUser)
                for (let role of this.rolesofUser) {           
                  for (let cat of this.catItem) {           
                    if (role.catId == cat.catId && (role.status == 1) && role.p_approve == true) {
                      this.isApprove = true       
                      console.log(this.isApprove)       
                    }
                    if (role.catId == cat.catId && (role.status == 1) && role.p_delete == true) {
                      this.isModDelete = true    
                      console.log(this.isApprove)          
                    }
                  } 
                }    
              }
             else {
          
             }
           }, err => {
             console.log(err)
          });     
        }
        else {
          this.error = res.message
        }
      }, err => {
        console.log(err)
      });
     
         
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
        // this.route.navigate(["/newsmanagement"])
        window.location.href = "/newsmanagement";
      }, err => {
        console.log(err)
      })  
  }

  togglecat(e){
    this.email = localStorage.getItem("email")
    console.log(this.email)
    if(e.target.checked == true){
      this.modService.addCatOnItem(this.id, e.target.value,this.email)
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