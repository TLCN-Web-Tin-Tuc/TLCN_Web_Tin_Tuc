import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../_entity/user';
import { Guest } from 'src/app/_entity/guest';
@Injectable({
  providedIn: 'root'
})
export class NuServiceService {
  context = environment.base_url
  constructor(private http: HttpClient) { }

  register(user: User) : Observable<any>{    
    return this.http.post(`${this.context}/api/v1/nuser/register/`,user);
  }
  checkEmail(email : string) : Observable<any> {
    return this.http.get(`${this.context}/api/v1/nuser/check-user/${email}`,{observe:`response`});
  }

  checkEmailPass(email : string, pass : string) : Observable<any> {
    return this.http.post(`${this.context}/api/v1/nuser/login/${email}/${pass}`,"");
  }

  login(guest: Guest) : Observable<any>{    
    return this.http.post(`${this.context}/api/v1/nuser/login/${guest.email}/${guest.password}`,{observe:`response`});
  }

  getCatItem(itemid : number) :Observable<any> {
    return this.http.get(`${this.context}/api/v1/nuser/getcatofitem/${itemid}`);
  }

  getItemDescDay() :Observable<any> {
    return this.http.get(`${this.context}/api/v1/nuser/get-item-desc-day`);
  }

  getItemDescDayAll() :Observable<any> {
    return this.http.get(`${this.context}/api/v1/nuser/get-item-desc`);
  }

  getAllItemsPage(id: number, page: number, size: number): Observable<any>{
    return this.http.get(`${this.context}/api/v1/nuser/itemsbycat?&page=${page}&size=${size}&id=${id}`);
  }

  getAllCatChecked() :Observable<any>{
    return this.http.get(`${this.context}/api/v1/nuser/cat/checked`);
  }

  getAllParentCatChecked() :Observable<any>{
    return this.http.get(`${this.context}/api/v1/nuser/cat/parentcatchecked`);
  }

  getItemDescLike() :Observable<any> {
    return this.http.get(`${this.context}/api/v1/nuser/get-item-desc-like`);
  }

  getChildCat(id: number) :Observable<any>{
    return this.http.get(`${this.context}/api/v1/nuser/cat/childcat/${id}`);
  }
}
