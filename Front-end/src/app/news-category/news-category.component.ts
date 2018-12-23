import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { first } from 'rxjs/operators';
import { CatItem } from '../_entity/catitem';
import { Observable } from 'rxjs';
import { Cat } from '../_entity/cat';

@Component({
  selector: 'app-news-category',
  templateUrl: './news-category.component.html',
  styleUrls: ['./news-category.component.css',
    '../../assets/magz-master/css/style.css',
    '../../assets/magz-master/css/skins/all.css',
    '../../assets/magz-master/css/demo.css',
    // '../../assets/magz-master/scripts/bootstrap/bootstrap.min.css',
    '../../assets/magz-master/scripts/magnific-popup/dist/magnific-popup.css',
    '../../assets/magz-master/scripts/owlcarousel/dist/assets/owl.theme.default.min.css',
    '../../assets/magz-master/scripts/owlcarousel/dist/assets/owl.carousel.min.css',
    '../../assets/magz-master/scripts/toast/jquery.toast.min.css',
    '../../assets/magz-master/scripts/ionicons/css/ionicons.min.css',
    '../../assets/magz-master/scripts/sweetalert/dist/sweetalert.css'
  ]

})
export class NewsCategoryComponent implements OnInit {
  catName: string;
  catList: Cat[];
  id : number;
  items: Observable<CatItem[]>;
  page: number;
  pageNumber: number;
  pageArray: Array<number> = [];
  size: number;
  current: number;

  itemCate:number = 0;

  constructor(private activatedRoute: ActivatedRoute, private nuService : NuServiceService) {
    this.page = 0;
    this.size = 8;
    this.current = 0;
   }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(queryParams => {
      this.id = +this.activatedRoute.snapshot.paramMap.get('id');     
    });

    this.getCat();

    this.getAllItemsPage(this.id, this.page, this.size);
  }

  getAllItemsPage(id: number, page: number, size: number){
    this.nuService.getAllItemsPage(id, page, size)
    .pipe(first()).subscribe(res=>{
      if(res.success == "true"){
        this.items = res.data;
        this.pageNumber = res.pageNumber;
        for (let index = 0; index < this.pageNumber; index++) {
          this.pageArray.push(index);
        }
        this.current = page;
      }
    },
    err=>{
      alert("Không lấy được danh sách tin tức !!!");
    });
  }

  onGotoPage(page: number) {
    this.pageArray = [];
    this.getAllItemsPage(this.id, page, this.size);
  }

  onPrePage() {
    this.current--;
    this.pageArray = [];
    this.getAllItemsPage(this.id, this.current, this.size);
  }

  onNextPage() {
    this.pageArray = [];
    this.current++;
    this.getAllItemsPage(this.id, this.current, this.size);
  }

  getCat() {
    this.nuService.getAllCatChecked()
      .subscribe(res => {
        this.catList = res.data;
        if (res.success == "true") {
          this.catList = res.data;
          for(let ca of this.catList)
          {
            if(ca.id == this.id)
            {
              this.catName = ca.name;
            }           
          }
        }
      }, err => {
        console.log(err.message)
      });
  }

}
