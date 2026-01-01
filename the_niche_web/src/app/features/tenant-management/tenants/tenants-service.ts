import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {TenantRequest, TenantResponse} from "./tenant-modal";
import {catchError, Observable, throwError} from "rxjs";
import {PageResponse} from "../../../shared/base.model";


@Injectable({providedIn:'root'})
export class TenantsService{
    private readonly API_URL = "http://localhost:8080/api/v1/tenants";

    constructor(private  http: HttpClient) {

    }
    createTenant(request: TenantRequest):Observable<TenantRequest> {
        return  this.http.post<TenantRequest>(this.API_URL,request)
            .pipe(catchError(this.handleError))
    }

    getTenantsPage(page: number, size: number):Observable<PageResponse<TenantResponse>> {
        return this.http.get<PageResponse<TenantResponse>>(this.API_URL+`/search?page=${page}&size=${size}`)
            .pipe(catchError(this.handleError));
    }

    private handleError(error:HttpErrorResponse){
        let errorMsg = '';
        if (error.error instanceof ErrorEvent) {
            errorMsg = `Client-side error: ${error.error.message}`;
        }else {
            // Backend error
            errorMsg = ` Server return code ${error.status}, body:was ${JSON.stringify(error.error)}`;
        }
        console.error(errorMsg);
        return throwError(()=> new Error(errorMsg));
    }
}
