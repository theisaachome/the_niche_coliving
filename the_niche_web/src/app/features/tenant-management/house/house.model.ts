

export interface HouseDetails {
    id: string;
    houseName: string;
    description: string;
    address: string;
    notes: string;
    createdBy: string;
    updatedBy: string;
    createdAt: string; // ISO 8601 string
    updatedAt: string;
    rooms: Room[];
}
export interface House {
    id?: number|string;
    houseName:string;
    description:string;
    address:string;
    notes:string;
    createdBy?: string;
    updatedBy?: string;
    createdAt?: string;
    updatedAt?: string;
}

// room.model.ts
export interface Room {
    id: string;
    room_number: string;
    capacity: number;
    notes: string;

    createdBy: string;
    updatedBy: string;
    createdDate: string; // ISO 8601 string, can be converted to Date
    updatedDate: string;
}

export interface HouseRoom {
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
export interface PageResponse<T> {
    content: T[];
    pageNo: number;
    pageSize: number;
    totalElement: number;
    totalPages: number;
    last: boolean;
}
