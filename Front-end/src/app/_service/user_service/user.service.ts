import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  context = environment.base_user_url;
  constructor(private http: HttpClient) { }

  doiMatKhau(email:string, oldPassword:string, newPassword:string) : Observable<any>{
    return this.http.post(`${this.context}/api/v1/user/doimatkhau/${email}/${oldPassword}/${newPassword}`,"");
  }

  getProfile(email:string) : Observable<any>{
    return this.http.get(`${this.context}/api/v1/user/profile/${email}`);
  }
}
