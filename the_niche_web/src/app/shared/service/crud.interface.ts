import {Observable} from "rxjs";

export interface CrudInterface<T>{
    getAll(): Observable<T[]>;
    getById(id: string | number): Observable<T>;
    delete(id: string | number): Observable<void>;
}
