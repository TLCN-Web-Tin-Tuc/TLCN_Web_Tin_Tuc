import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../_entity/user';
@Injectable({
  providedIn: 'root'
})
export class NuServiceService {
  context = environment.base_url
  constructor(private http: HttpClient) { }

  //register(user: User) : Observable<any>{    
  //  return this.http.post(`${this.context}/api/v1/nuser/login/${guest.email}/${guest.password}`,{observe:`response`});
  //}
}