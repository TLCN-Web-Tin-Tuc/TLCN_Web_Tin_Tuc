import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../_entity/user';
@Injectable({
  providedIn: 'root'
})
export class HeaderService {
  context = environment.base_url
  constructor(private http: HttpClient) { }

  findUser(email: string) : Observable<any>{    
    return this.http.get(`${this.context}/api/v1/nuser/find-user/${email}`,{observe:`response`});
  }
  
}
