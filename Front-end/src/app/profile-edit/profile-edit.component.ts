import { Component, OnInit } from '@angular/core';
import { User } from '../_entity/user';
import { Router } from '@angular/router';
import { UserService } from '../_service/user_service/user.service';
import { first } from 'rxjs/operators';
import { FormBuilder ,FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { Role } from '../_entity/role';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {

  selectedImg : string = ""
  user: User;
  error: string;
  email: string;
  formImage: FormGroup;
  image: string = "";
  imageUploaded: string = "";

  constructor(private userService : UserService, private fb: FormBuilder) {
    this.user = new User();
    this.formImage = this.fb.group({
      name: ['', Validators.required],
      avatar: null
    });
   }

  ngOnInit() {
    this.email = localStorage.getItem("email")
    this.userService.getProfile(this.email)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "true")
      {
        this.user = res.data;    
        if(this.user.avatar != null)
          this.user.avatar = res.data.avatar
        else
          this.user.avatar = "/assets/robust-admin/profile.png"
        this.user.dateTemp = moment(res.data.dateOfBirth).format("YYYY-MM-DD").toString();
        this.selectedImg = this.user.avatar
      }
      else
      {
          this.error = res.message
      }
    }, err => {
      console.log(err)
    })    
  }

  updateProfile(){
    this.user.dateOfBirth = new Date(this.user.dateTemp)
    this.userService.updateProfile(this.user)
    .pipe(first())
    .subscribe(res => {
      if(res.success == "true")
      {
        alert("Cập nhật thông tin cá nhân thành công !!!")
        localStorage.setItem("lastName",this.user.lastName)
           
      }
      else
      {
        alert("Cập nhật thông tin cá nhân không thành công !!!")
      }
    }, err => {
      alert("Cập nhật thông tin cá nhân không thành công !!!")
    })    
  }

  onSaveImage(){
    this.imageUploaded = this.formImage.get('avatar').value.value;
    this.user.avatar = this.imageUploaded;
    this.user.email = localStorage.getItem("email")
    localStorage.setItem("avatar",this.user.avatar)
    this.userService.changeAvatar(this.user).pipe(first())
    .subscribe(res=>{
      if(res.success == "true")
        alert("Cập nhật ảnh đại diện thành công !!!");
      else
        alert("Cập nhật ảnh đại diện không thành công !!!");
    },
    err=>{
        alert("Không cập nhật được ảnh đại diện !!!");
    });
  }

  onChangedImage(event){
    let reader = new FileReader();
    if(event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
    
      reader.readAsDataURL(file);
       reader.onload = () => {
        this.image = "data:" + file.type + ";base64," + reader.result.split(',')[1];
        this.selectedImg = this.image
        this.formImage.get('avatar').setValue({
          filename: file.name,
          filetype: file.type,
          value: "data:" + file.type + ";base64," + reader.result.split(',')[1]
        })
      };
    }
  }

  
}
