import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


export  class BaseService<T>{

   constructor(protected http: HttpClient,private baseUrl:string)  {
   }

   getAll():Observable<T[]>{
    return this.http.get<T[]>(`${this.baseUrl}/units`,{})
   }

   getById():Observable<T>{
     return this.http.get<T>(this.baseUrl,{});
   }
  create(data: T): Observable<T> {
    return this.http.post<T>(this.baseUrl, data);
  }

  update(id: number, data: T): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

}
