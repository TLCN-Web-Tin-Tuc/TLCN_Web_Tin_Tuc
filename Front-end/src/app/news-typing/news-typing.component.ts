import { Component, OnInit } from '@angular/core';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { Item } from '../_entity/item';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { FormBuilder ,FormGroup, Validators } from '@angular/forms';
import * as ClassicEditorBuild from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-news-typing',
  templateUrl: './news-typing.component.html',
  styleUrls: ['./news-typing.component.css']
})
export class NewsTypingComponent implements OnInit {

  public Editor = ClassicEditorBuild;
  item: Item;
  error: string;
  formImage: FormGroup;
  image: string = "";
  imageUploaded: string = "";
  
  constructor(private modService : ModServiceService, private fb: FormBuilder) { 
    this.item = new Item();
    this.formImage = this.fb.group({
      name: ['', Validators.required],
      avatar: null
    });
  }

  ngOnInit() {
    this.item.fullDesc = "";
  }

  onChangedImage(event){
    let reader = new FileReader();
    if(event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
       reader.onload = () => {
        this.image = "data:" + file.type + ";base64," + reader.result.split(',')[1];
        this.formImage.get('avatar').setValue({
          filename: file.name,
          filetype: file.type,
          value: "data:" + file.type + ";base64," + reader.result.split(',')[1]
        })
      };
    }
  }
  
  onSaveImage(){
    this.imageUploaded = this.formImage.get('avatar').value.value;
    this.item.image = this.imageUploaded;
    this.modService.createItem(this.item).pipe(first())
    .subscribe(res=>{
      if(res.success == "true")
        alert("Thêm bài viết thành công !!!");
      else
        alert("Thêm bài viết không thành công !!!");
    },
    err=>{
        alert("Không Thêm được bài viết !!!");
    });
  }

}
