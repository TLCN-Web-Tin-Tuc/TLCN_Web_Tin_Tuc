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

  register(user: User) : Observable<any>{    
<<<<<<< HEAD
    return this.http.post(`${this.context}/api/v1/nuser/register`,user,{observe:`response`});
=======
    return this.http.post(`${this.context}/api/v1/nuser/register/`,user);
>>>>>>> ba9bc73c79c0564b823e5856e143150dce86e3a0
  }
  checkEmail(email : string) : Observable<any> {
    return this.http.get(`${this.context}/api/v1/nuser/check-user/${email}`,{observe:`response`});
  }
}
