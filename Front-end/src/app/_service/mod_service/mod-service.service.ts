import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Item } from '../../_entity/item';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModServiceService {

  context = environment.base_mod_url
  constructor(private http: HttpClient) { }

  // login(guest: Item) : Observable<any>{    
  //   return this.http.post(`${this.context}/api/v1/mod/login/${guest.email}/${guest.password}`,{observe:`response`});
  // }

}
