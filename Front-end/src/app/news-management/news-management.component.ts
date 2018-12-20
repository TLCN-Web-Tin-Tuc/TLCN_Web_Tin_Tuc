import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';


import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { Router } from '@angular/router';
import { Item } from '../_entity/item';

@Component({
  selector: 'app-news-management',
  templateUrl: './news-management.component.html',
  styleUrls: ['./news-management.component.css']
})
export class NewsManagementComponent implements OnInit {

    // Our array of clients
    items: Item[];
    dataTable: any;

  constructor(private router : Router,private modService: ModServiceService, private chRef: ChangeDetectorRef) { }

  ngOnInit(){
    this.modService.getListItem()
      .subscribe(res => {
        if(res.success == "true")
        {
          
          this.items = res.data;
          
        }

        // You'll have to wait that changeDetection occurs and projects data into 
        // the HTML template, you can ask Angular to that for you ;-)
        this.chRef.detectChanges();

        // Now you can use jQuery DataTables :
        const table: any = $('table');
        this.dataTable = table.DataTable();
         }, err => {
          console.log(err.message)
      });
  }

  onGotoItemDetail(id) {
    this.router.navigate(["/newsdetail"], { queryParams: { id: id } });
  }
}
