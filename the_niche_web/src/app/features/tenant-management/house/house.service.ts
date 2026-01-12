import {Injectable} from "@angular/core";
import {House, HouseDetailResponse, HouseDetails, HouseOverviewResponse, HouseResponse} from "./house.model";
import {BehaviorSubject, Observable} from "rxjs";
import {BaseService} from "../../../shared/service/base.service";
import {HOUSE_API} from "../../../shared/api-constants";
import {CrudInterface} from "../../../shared/service/crud.interface";
import {HttpClient} from "@angular/common/http";


interface HouseRequest {
    name:string;
    location:string;
    remark:string;
}


@Injectable({providedIn: 'root'})
export class HouseService implements CrudInterface<any>{

    constructor(private http: HttpClient) {}

    getAll(): Observable<HouseResponse[]> {
        return this.http
            .get<HouseResponse[]>(HOUSE_API);
    }
    getById(id: string | number): Observable<HouseDetailResponse> {
        return  this.http.get<HouseDetailResponse>(   `${HOUSE_API}/${id}`);
    }
    delete(id: string | number): Observable<void> {
        throw new Error("Method not implemented.");
    }
    saveHouse(data:HouseRequest):Observable<any>{
       return  this.http.post(HOUSE_API,data);
    }
    updateHouse(id:string,data:HouseRequest): Observable<any>{
       return  this.http.put(`${HOUSE_API}/${id}`,data);
    }
    getAllHouseOverview():Observable<HouseOverviewResponse[]>{
            return this.http
                .get<HouseOverviewResponse[]>(HOUSE_API+`/overviews`);
    }

}

