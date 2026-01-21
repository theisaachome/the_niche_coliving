import {BaseModel} from "../../../shared/base.model";




export const ROOM_STATUS = [
    'AVAILABLE',
    'OCCUPIED',
] as const;

export type RoomStatus = typeof ROOM_STATUS[number];

export const ROOM_TYPE = [
    'DOM',
    'DOUBLE',
    'SINGLE'
] as const;

export type RoomType = typeof  ROOM_TYPE[number];

export  interface RoomResponse extends BaseModel{
    roomNumber: string;
    roomType: RoomType;
    roomCode: string;
    capacity: number;
    roomStatus: RoomStatus;
    remark:string;
}

export  interface RoomRequest{
    roomNumber: string;
    roomType: RoomType;
    capacity: number;
    remark:string;
}

export interface RoomUpdateRequest extends BaseModel{
    roomNumber: string;
    roomType: RoomType;
    capacity: number;
    remark:string;
}
