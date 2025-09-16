import {Injectable} from "@angular/core";
import {House, HouseDetails, HouseRoom, UnitsSummary} from "./house.model";
import {BehaviorSubject, Observable} from "rxjs";
import {BaseService} from "../../../core/services/base.service";
import {HttpClient} from "@angular/common/http";



const url="http://localhost:8080/api/v1/";

@Injectable({providedIn: 'root'})
export class HouseService extends BaseService<House>{

    constructor(http: HttpClient) {
        super(http,url+"houses");
        // super(http, `/api/v1/houses`);
    }

    addNewUnit(data:House){
        console.log("Saving New Unit!");
    }

    getAllHouses(){

    }

    getHouseDetailsById(id:string):Observable<HouseDetails>{
       return  this.http.get<HouseDetails>(url+"houses/"+id);
    }


    getUnitSummary(){
        return new BehaviorSubject<UnitsSummary[]>([]);
    }
}

