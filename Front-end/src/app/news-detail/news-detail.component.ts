import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from '../_entity/item';
import { Role } from '../_entity/role';
import { User } from '../_entity/user';
import { UserService } from '../_service/user_service/user.service';

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

  constructor(private modService: ModServiceService, private activatedRoute: ActivatedRoute, private route: Router, private userService: UserService) {
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
            this.selectedImg = this.item.image;
            this.mod = "true"

            if (this.item.status == 1) {
              this.kichhoat = "Bỏ duyệt bài";
            }
            else {
              this.kichhoat = "Duyệt bài";
            }
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
}
