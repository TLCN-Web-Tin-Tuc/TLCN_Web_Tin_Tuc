import { Component, OnInit } from '@angular/core';
import { HeaderService } from '../_service/header/header.service';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username : string
  email : string
  error : String
  constructor(private headerService : HeaderService, private route : Router) { 
   
  }


  ngOnInit() {
    this.username = localStorage.getItem("lastName");
   
  }
  findUser()  {
    this.headerService.findUser(this.email)
      .pipe(first())
      .subscribe(res => {
        if(res.success == "true")
        {
          this.username = res.data.lastName
        }
        else
        {
            this.error = res.message
        }
      }, err => {
        console.log(err)
      })      
  }

}
