import { Component, OnInit } from '@angular/core';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { Item } from '../_entity/item';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-news-typing',
  templateUrl: './news-typing.component.html',
  styleUrls: ['./news-typing.component.css']
})
export class NewsTypingComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  title: string;
  shortDesc: string;
  fullDesc: string; 
  
  constructor(private modService : ModServiceService, private route : Router) { 
  }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  createItem(){
    this.currentFileUpload = this.selectedFiles.item(0);
    this.title = this.title;
    this.shortDesc = this.shortDesc;
    this.fullDesc = this.fullDesc;
    this.modService.createItem(this.title, this.shortDesc, this.fullDesc, this.currentFileUpload).pipe(first()).subscribe(res => {
      alert("Tạo bài viết thành công !!!");
      this.title = "";
      this.shortDesc = "";
      this.fullDesc = "";
    },
    err => {
      console.log(err);
    })
  }
}
