import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from './admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private _http:HttpClient) { }
  public loginAdminFromRemote(admin:Admin):Observable<any>
  {
    console.log("In admin service :"+admin.username);
    console.log("In admin service :"+admin.password);

      return this._http.post("http://localhost:8080/zonions/loginadmin",admin);
}
}
