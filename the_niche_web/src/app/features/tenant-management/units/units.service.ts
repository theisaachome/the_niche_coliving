import {Injectable} from "@angular/core";
import {Unit, UnitRoom, UnitsSummary} from "./unit.model";
import {BehaviorSubject, Observable} from "rxjs";


@Injectable({providedIn: 'root'})
export class UnitsService {

    addNewUnit(data:Unit){
        console.log("Saving New Unit!");
        units.push(data);
    }

    getAllUnits():Observable<Unit[]>{
        return new BehaviorSubject<Unit[]>(units);
    }

    getUnitSummary(){
        return new BehaviorSubject<UnitsSummary[]>(unitsSummary);
    }
}

const unitsSummary:UnitsSummary[]=[
    {
        "id": 100,
        "unitName": "Green Villa",
        "address": "123 Main Street",
        "numberOfRooms": 5,
        "totalCapacity": 13,
        "notes": "Renovation next month"
    },
    {
        "id": 101,
        "unitName": "Blue Condo",
        "address": "45 Ocean Ave",
        "numberOfRooms": 5,
        "totalCapacity": 12,
        "notes": "Fully occupied"
    },
    {
        "id": 102,
        "unitName": "City Hostel",
        "address": "88 Downtown Road",
        "numberOfRooms": 10,
        "totalCapacity": 32,
        "notes": "Available for booking"
    },
    {
        "id": 103,
        "unitName": "Sunset Apartments",
        "address": "12 Palm Street",
        "numberOfRooms": 8,
        "totalCapacity": 20,
        "notes": "Partially occupied"
    },
    {
        "id": 104,
        "unitName": "Hilltop Residence",
        "address": "99 Mountain View",
        "numberOfRooms": 6,
        "totalCapacity": 18,
        "notes": "Newly opened"
    }
]

//
// const units:Unit[]=[
//     {
//         "id": 1,
//         "unit_name": "Unit A1",
//         "description": "A modern coliving unit offering shared and private rooms, close to public transport and shopping areas.",
//         "address": "123 Highway 65, Springfield",
//         "notes": "Ground floor, near main entrance"
//     },
//     {
//         "id": 2,
//         "unit_name": "Unit B2",
//         "description": "A modern coliving unit offering shared and private rooms, close to public transport and shopping areas.",
//         "address": "456 River Road, Springfield",
//         "notes": "Second floor, corner unit"
//     },
//     {
//         "id": 3,
//         "unit_name": "Unit C3",
//         "description": "A modern coliving unit offering shared and private rooms, close to public transport and shopping areas.",
//         "address": "789 Hilltop Avenue, Springfield",
//         "notes": "Top floor with balcony"
//     },
//     {
//         "id": 4,
//         "unit_name": "Unit D4",
//         "description": "A modern coliving unit offering shared and private rooms, close to public transport and shopping areas.",
//         "address": "321 Garden Street, Springfield",
//         "notes": "Facing the park, quiet area"
//     }
// ]

const units: Unit[] = [
    {
        id: 1,
        unit_name: "Green Villa",
        description: "A cozy villa with garden",
        address: "123 Main Street",
        notes: "Renovation next month"
    },
    {
        id: 2,
        unit_name: "Blue Condo",
        description: "High-rise apartment near the beach",
        address: "45 Ocean Ave",
        notes: "Fully occupied"
    },
    {
        id: 3,
        unit_name: "City Hostel",
        description: "Budget-friendly hostel in city center",
        address: "88 Downtown Road",
        notes: "Available for booking"
    }
];

const unitRooms: UnitRoom[] = [
    { id: 1, room_number: "101", capacity: 2, notes: "Sea view", },
    { id: 2, room_number: "102", capacity: 3, notes: "", },
    { id: 3, room_number: "201", capacity: 2, notes: "", },
    { id: 4, room_number: "202", capacity: 2, notes: "", },
    { id: 5, room_number: "301", capacity: 4, notes: "Penthouse", },

    { id: 6, room_number: "A1", capacity: 2, notes: "", },
    { id: 7, room_number: "A2", capacity: 2, notes: "", },
    { id: 8, room_number: "B1", capacity: 3, notes: "", },
    { id: 9, room_number: "B2", capacity: 3, notes: "", },
    { id: 10, room_number: "C1", capacity: 2, notes: "", },

    { id: 11, room_number: "H1", capacity: 6, notes: "Dormitory", },
    { id: 12, room_number: "H2", capacity: 6, notes: "Dormitory", },
    { id: 13, room_number: "H3", capacity: 4, notes: "", },
    { id: 14, room_number: "H4", capacity: 4, notes: "", },
    { id: 15, room_number: "H5", capacity: 2, notes: "", },
    { id: 16, room_number: "H6", capacity: 2, notes: "", },
    { id: 17, room_number: "H7", capacity: 2, notes: "", },
    { id: 18, room_number: "H8", capacity: 2, notes: "", },
    { id: 19, room_number: "H9", capacity: 2, notes: "", },
    { id: 20, room_number: "H10", capacity: 2, notes: "", }
];
