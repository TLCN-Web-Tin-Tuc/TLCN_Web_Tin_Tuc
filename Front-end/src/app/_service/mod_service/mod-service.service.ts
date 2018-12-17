import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Item } from '../../_entity/item';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ModServiceService {

  context = environment.base_mod_url
  
  constructor(private http: HttpClient) { }

  createItem(title: string, shortDesc: string, fullDesc: string, file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('title', title);
    formdata.append('shortDesc', shortDesc);
    formdata.append('fullDesc', fullDesc);
    formdata.append('file', file);

    return this.http.post(`${this.context}/api/v1/mod/items/create`, formdata, {observe:`response`});
  }
}
