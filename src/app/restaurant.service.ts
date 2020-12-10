import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private baseUrl = 'http://localhost:8080/zonions/restaurants';

  private imageUrl = 'http://localhost:8080/zonions/imgrestaurant';
  //http://localhost.8080/zonions/imgrestaurant/

  constructor(private http: HttpClient) { }

  getRestaurant(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createRestaurant(restaurant: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, restaurant);
  }

  updateRestaurant(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteRestaurant(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getRestaurantsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  pushFile(file:any, id:number):any
  {
    let target:DataTransfer=<DataTransfer>(file.target);
    let fileList:FileList=target.files;
    let file1:File=fileList[0];
    const formdata: FormData = new FormData();

    formdata.append('file',file1,file1.name);

    console.log("formdata..."+formdata);

    const req = new HttpRequest('PUT',`${this.imageUrl}/${id}`, formdata,
    {
      reportProgress:true,
      responseType:'text'
    });

    console.log("request object : "+req);

    return this.http.request(req);
  }
}