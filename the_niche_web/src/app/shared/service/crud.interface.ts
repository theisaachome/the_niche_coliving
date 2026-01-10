import {Observable} from "rxjs";
import {BaseModel} from "../base.model";

export interface CrudInterface<T extends BaseModel>{
    getAll(): Observable<T[]>;
    getById(id: string | number): Observable<T>;
    delete(id: string | number): Observable<void>;
}
