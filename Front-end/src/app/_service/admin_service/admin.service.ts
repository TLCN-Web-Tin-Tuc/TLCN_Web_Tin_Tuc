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

  setStatus(id,userUpdate) : Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/users/status/${id}/${userUpdate}`);
  }

  findRoleById(id) : Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/roles/${id}`);
  }

  getAllRole() :Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/roles`);
  }

  updateUserRole(uid,rid) :Observable<any>{
    return this.http.put(`${this.context}/api/v1/admin/users/role/${uid}/${rid}`,"");
  }

  createRole(role : Role, userCreate): Observable<any>{    
    return this.http.post(`${this.context}/api/v1/admin/roles/createrole/${userCreate}`,role);
  }

  updateRole(role : Role, userUpdate): Observable<any>{    
    return this.http.post(`${this.context}/api/v1/admin/roles/updaterole/${userUpdate}`,role);
  }

  updateStatusRole(rid,userUpdate): Observable<any>{    
    return this.http.put(`${this.context}/api/v1/admin/roles/updatestatus/${rid}/${userUpdate}`,"");
  }

  updateRoleCreate(rid, userUpdate) :Observable<any>{
    return this.http.put(`${this.context}/api/v1/admin/roles/updaterolecreate/${rid}/${userUpdate}`,"");
  }

  updateRoleUpdate(rid, userUpdate) :Observable<any>{
    return this.http.put(`${this.context}/api/v1/admin/roles/updateroleupdate/${rid}/${userUpdate}`,"");
  }

  updateRoleDelete(rid, userUpdate) :Observable<any>{
    return this.http.put(`${this.context}/api/v1/admin/roles/updateroledelete/${rid}/${userUpdate}`,"");
  }

  updateRoleApprove(rid, userUpdate) :Observable<any>{
    return this.http.put(`${this.context}/api/v1/admin/roles/updateroleapprove/${rid}/${userUpdate}`,"");
  }

  updateRoleAdmin(rid, userUpdate) :Observable<any>{
    return this.http.put(`${this.context}/api/v1/admin/roles/updateroleadmin/${rid}/${userUpdate}`,"");
  }

  getAllCat() :Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/cat`);
  }
}
