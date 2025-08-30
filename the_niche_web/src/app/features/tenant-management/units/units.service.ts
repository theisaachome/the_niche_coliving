import {Injectable} from "@angular/core";
import {Unit} from "./unit.model";
import {BehaviorSubject, Observable} from "rxjs";


@Injectable({providedIn: 'root'})
export class UnitsService {

    addNewUnit(data:Unit){
        console.log("Saving New Unit!");
        data.id=new Date().getUTCMilliseconds();
        units.push(data);
        console.log(units)
    }

    getAllUnits():Observable<Unit[]>{
        return new BehaviorSubject<Unit[]>(units);
    }
}


const units:Unit[]=[
    {
        "id": 1,
        "name": "Unit A1",
        "address": "123 Highway 65, Springfield",
        "notes": "Ground floor, near main entrance"
    },
    {
        "id": 2,
        "name": "Unit B2",
        "address": "456 River Road, Springfield",
        "notes": "Second floor, corner unit"
    },
    {
        "id": 3,
        "name": "Unit C3",
        "address": "789 Hilltop Avenue, Springfield",
        "notes": "Top floor with balcony"
    },
    {
        "id": 4,
        "name": "Unit D4",
        "address": "321 Garden Street, Springfield",
        "notes": "Facing the park, quiet area"
    }
]
