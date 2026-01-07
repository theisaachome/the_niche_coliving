import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageResponse} from "../base.model";
import {Injectable} from "@angular/core";

@Injectable()
export abstract class BaseService<TResponse>{
    protected abstract url(): string;

    protected constructor(
        protected http: HttpClient
    ) {}

    getAll(): Observable<TResponse[]> {
        return this.http.get<TResponse[]>(this.url());
    }
    getById(id: string | number): Observable<TResponse> {
        return this.http.get<TResponse>(`${this.url()}/${id}`);
    }
    delete(id: string | number): Observable<void> {
        return this.http.delete<void>(`${this.url()}/${id}`);
    }

    getAllPagedList(): Observable<PageResponse<TResponse>> {
        return this.http.get<PageResponse<TResponse>>(this.url());
    }
}
