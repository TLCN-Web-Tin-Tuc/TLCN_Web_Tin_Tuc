import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role } from 'src/app/_entity/role';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  context = environment.base_admin_url;
  constructor(private http: HttpClient) { }

  getListUser() : Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/users`);
  }

  findUserById(id) : Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/users/search?id=${id}`);
  }

  setStatus(id) : Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/users/status/${id}`);
  }

  getAllRole() :Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/roles`);
  }

  updateUserRole(uid,rid) :Observable<any>{
    return this.http.put(`${this.context}/api/v1/admin/users/role/${uid}/${rid}`,"");
  }

  createRole(role : Role): Observable<any>{    
    return this.http.post(`${this.context}/api/v1/admin/roles/createrole`,role);
  }
}
