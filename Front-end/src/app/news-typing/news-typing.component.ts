import { Component, OnInit } from '@angular/core';
import { ModServiceService } from '../_service/mod_service/mod-service.service';
import { Item } from '../_entity/item';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { FormBuilder ,FormGroup, Validators } from '@angular/forms';
import * as ClassicEditorBuild from '@ckeditor/ckeditor5-build-classic';
import { UserService } from '../_service/user_service/user.service';
import { Role } from '../_entity/role';
import { NuServiceService } from '../_service/nu_service/nu-service.service';

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
  email : string
  isCreate : boolean = false
  rolesofUser : Role[]
  pass : string
  
  constructor(private modService : ModServiceService, private router : Router, private fb: FormBuilder,
     private userService : UserService, private nuService : NuServiceService) { 
    this.item = new Item();
    this.formImage = this.fb.group({
      name: ['', Validators.required],
      avatar: null
    });
  }

  ngOnInit() {
    this.item.fullDesc = "";
    this.checkEmail()
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


  async checkEmail(){
    await this.checkUserAndPassWord()
    this.email = localStorage.getItem("email")
    if(this.email == null)
    {
      this.router.navigate(["/"])
    }
    this.userService.getProfile(this.email)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "true")
      {                     
        this.rolesofUser = res.data.roles
        for(let role of this.rolesofUser)
        {
          if(role.p_create == true && role.status == 1)
          {            
            this.isCreate = true;
            return
          }           
        }
        if(this.isCreate == false){
          alert("Bạn không được truy cập vào trang này")
          this.router.navigate(["/"])
        } 
      }
      else
      {
          this.error = res.message
      }
    }, err => {
      console.log(err)
    })  

  }

  checkUserAndPassWord(){
    this.email = localStorage.getItem("email")
    this.pass = localStorage.getItem("password")
    this.nuService.checkEmailPass(this.email, this.pass)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "false")
      {            
        localStorage.clear()
        this.router.navigate(["/"]);
      }
      
    }, err => {
      console.log(err)
    })      
  }
}
