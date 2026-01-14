import {BaseModel} from "../../../shared/base.model";

export const HOUSE_STATUS=[
    'ACTIVE',
    'INACTIVE',
    'MAINTENANCE',
    'CLOSED'
] as const;

export type HouseStatus = typeof HOUSE_STATUS[number];

export const HOUSE_STATUS_META: Record<HouseStatus,{ label: string; icon: string }> = {
    ACTIVE: {
        label: 'Active',
        icon: 'check circle green'
    },
    INACTIVE: {
        label: 'Inactive',
        icon: 'pause circle grey'
    },
    MAINTENANCE: {
        label: 'Maintenance',
        icon: 'tools orange'
    },
    CLOSED: {
        label: 'Closed',
        icon: 'times circle red'
    }
};


export interface HouseDetails extends BaseModel {
    houseName: string;
    houseCode:string;
    houseStatus:string;
    address: string;
    remark: string;
    roomOptions: Room[];
}
export interface House {
    id?: number|string;
    houseName:string;
    houseCode:string;
    houseStatus:string;
    description:string;
    address:string;
    remark:string;
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

export interface HouseResponse{
    id?: string;
    houseCode: string;
    name: string;
    location: string;
    remark: string;
    houseStatus: string;
}

export interface  HouseOverviewResponse extends BaseModel{
    houseCode: string;
    name: string;
    location: string;
    houseStatus: HouseStatus;
    totalRooms: number;
    availableRooms: number;
}
export interface  HouseDetailResponse extends  BaseModel{
    houseCode: string;
    name: string;
    location: string;
    remark: string;
    status: HouseStatus;
    deleted: boolean;
}
