import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuServiceService } from '../_service/nu_service/nu-service.service';
import { Item } from '../_entity/item';
import { first } from 'rxjs/operators';
import { CatItem } from '../_entity/catitem';

@Component({
  selector: 'app-news-index',
  templateUrl: './news-index.component.html',
  styleUrls: ['./news-index.component.css',
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
export class NewsIndexComponent implements OnInit {

  itemHot : Item
  catItemchinh : CatItem[]
  itemDescDay : Item[]
  title : string = ""
  selectedImg: string = ""
  constructor(private route: Router,  private nuService : NuServiceService) { 
    this.itemHot = new Item()
  }

  ngOnInit() {
    this.loadItemChinh()
  }

  loadItemChinh(){
    this.nuService.getItemDescDay()
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
          this.itemDescDay = res.data
          this.itemHot = this.itemDescDay[0]
          this.title = this.itemHot.title
          this.selectedImg = this.itemHot.image
          console.log(this.itemHot)
          this.nuService.getCatItem(this.itemHot.id.toString())
          .pipe(first())
          .subscribe(res => {
             if (res.success == "true") {
                this.catItemchinh = res.data
              }
             else {
          
             }
           }, err => {
             console.log(err)
          })
        }
        else {
          
        }
      }, err => {
        console.log(err)
      })

  }

}
