import {Injectable} from "@angular/core";
import {House, HouseDetails} from "./house.model";
import {BehaviorSubject, Observable} from "rxjs";
import {BaseService} from "../../../shared/service/base.service";
import {HOUSE_API} from "../../../shared/api-constants";


interface CreateHouse {
    houseName:string;
    description:string;
    address:string;
    notes:string;
    rooms:[{
        room_name:string,
        capacity:number,
        notes:string}]
}


@Injectable({providedIn: 'root'})
export class HouseService extends BaseService<House>{
    protected override url(): string {
       return HOUSE_API;
    }

    saveHouse(data:CreateHouse):Observable<any>{
       return  this.http.post(this.url(),data);
    }

    getHouseDetailsById(id:string):Observable<HouseDetails>{
       return  this.http.get<HouseDetails>(`${this.url()}/${id}/details`);
    }
}

