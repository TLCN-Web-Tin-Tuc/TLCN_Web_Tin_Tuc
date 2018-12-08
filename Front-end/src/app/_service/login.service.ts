import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Guest } from '../_entity/guest';


@Injectable({
  providedIn: 'root'
})
export class LoginService { 
  context = environment.base_url
  constructor(private http: HttpClient) { }

  login(guest: Guest) : Observable<any>{    
    return this.http.post(`${this.context}/api/v1/nuser/login/${guest.email}/${guest.password}`,{observe:`response`});
  }

}

