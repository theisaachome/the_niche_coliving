import {BaseModel} from "../../../shared/base.model";


export interface HouseDetails extends BaseModel {
    houseName: string;
    houseCode:string;
    houseStatus:string;
    description: string;
    address: string;
    notes: string;
    rooms: Room[];
}
export interface House {
    id?: number|string;
    houseName:string;
    houseCode:string;
    houseStatus:string;
    description:string;
    address:string;
    notes:string;
    createdBy?: string;
    updatedBy?: string;
    createdAt?: string;
    updatedAt?: string;
}

// room.model.ts
export interface Room extends BaseModel {
    room_number: string;
    capacity: number;
    notes: string;
}

export interface HouseRoom {
    room_number: string;
    capacity:number;
    notes:string;
}
