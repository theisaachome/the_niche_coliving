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
    roomCode: string;
    roomType: RoomType;
    roomStatus: RoomStatus;
    capacity: number;
    remark:string;
}
