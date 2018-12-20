import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Item } from '../../_entity/item';

@Injectable({
  providedIn: 'root'
})
export class ModServiceService {
  context = environment.base_mod_url
  constructor(private http: HttpClient) { }

  createItem(item: Item): Observable<any>{
    return this.http.post(`${this.context}/api/v1/mod/items/create`, item);
  }

  getListItem() : Observable<any>{
    return this.http.get(`${this.context}/api/v1/mod/items`);
  }

  findItemById(id) : Observable<any>{
    return this.http.get(`${this.context}/api/v1/mod/items/search?id=${id}`);
  }
}
