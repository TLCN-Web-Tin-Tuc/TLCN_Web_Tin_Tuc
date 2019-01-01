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
  
  itemLast1 : Item
  itemLast2 : Item 
  itemLast3 : Item
  itemLast4 : Item
  itemLike : Item[]
  showItemLike : Item[]
  catItemchinh : CatItem[]
  itemDescDay : Item[]
  i : number = 1
 
  title : string = ""
  selectedImg: string = ""
  constructor(private route: Router,  private nuService : NuServiceService) { 
    this.itemHot = new Item()
    this.itemLast1 = new Item()
    this.itemLast2 = new Item() 
    this.itemLast3 = new Item()
    this.itemLast4 =  new Item()
  
  }

  ngOnInit() {
    this.loadItemChinh()
    this.loadItemLike()
  }

  loadItemChinh(){
    this.nuService.getItemDescDay()
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
          this.itemDescDay = res.data
          if(this.itemDescDay.length < 1){
            this.itemHot.isNull = true
          }else{
            this.itemHot = this.itemDescDay[0]          
            this.selectedImg = this.itemHot.image
          }
          if(this.itemDescDay.length < 2){            
            this.itemLast1.isNull = true
          }else{           
            this.itemLast1 = this.itemDescDay[1]
          }

          if(this.itemDescDay.length < 3){            
            this.itemLast2.isNull = true
          }else{           
            this.itemLast2 = this.itemDescDay[2] 
          }

          if(this.itemDescDay.length < 4){            
            this.itemLast3.isNull = true
          }else{           
            this.itemLast3 = this.itemDescDay[3]  
          }
          
          if(this.itemDescDay.length < 5){            
            this.itemLast4.isNull = true
          }else{           
            this.itemLast4 = this.itemDescDay[4] 
          }
        
          
          


          this.nuService.getCatItem(this.itemHot.id)
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
  loadItemLike(){
    this.nuService.getItemDescLike()
    .pipe(first())
    .subscribe(res => {
      if (res.success == "true") {
        this.itemLike = res.data        
        while(this.itemLike.length > 6){
          this.itemLike.pop()

        }
       
      }
      else {
        
      }
    }, err => {
      console.log(err)
    })

  }
  
  

}
