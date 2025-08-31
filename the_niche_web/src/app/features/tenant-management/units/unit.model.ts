


export interface Unit{
    id?: number;
    unit_name:string;
    description:string;
    address:string;
    notes:string;
}

export interface UnitRoom{
    id?: number;
    room_number: string;
    capacity:number;
    notes:string;
}

export interface UnitsSummary{
    id?: number;
    unitName:string;
    address:string;
    numberOfRooms:number;
    totalCapacity:number;
    notes:string;
}
