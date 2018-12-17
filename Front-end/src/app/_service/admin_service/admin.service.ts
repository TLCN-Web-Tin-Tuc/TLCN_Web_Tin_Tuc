import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  context = environment.base_admin_url;
  constructor(private http: HttpClient) { }

  getListUser() : Observable<any>{
    return this.http.get(`${this.context}/api/v1/admin/users`);
  }
}
