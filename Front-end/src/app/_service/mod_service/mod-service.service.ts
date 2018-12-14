import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ModServiceService {

  context = environment.base_mod_url
  constructor(private http: HttpClient) { }
}
