import {BaseModel} from "../../../shared/base.model";
import {RoomType} from "../rooms/room.model";


export interface RoomAssignmentOverviewResponse extends BaseModel {
    tenantName: string;
    assignmentStatus: String;
    startDate: string;      // ISO date: yyyy-MM-dd
    endDate?: string;       // optional (null from backend)
    depositAmount: number;
}

export interface  RoomAssignmentDetailsResponse extends BaseModel {
    houseName: string;
    roomName: string;
    tenantName: string;
    assignmentStatus: String;
    startDate: string;
    endDate?: string;
    depositAmount: number;
}


export interface RoomAssignmentListResponse {
    assignmentId: string;   // UUID

    houseId: string;
    houseName: string;

    roomId: string;
    roomCode: string;
    roomType: RoomType;

    tenantId: string;
    tenantName: string;

    startDate: string;      // ISO date (yyyy-MM-dd)
    endDate: string;        // ISO date (yyyy-MM-dd)
    status: AssignmentStatus;

    monthlyRent: number;    // BigDecimal → number
}

export enum AssignmentStatus {
    ACTIVE = 'ACTIVE',
    TERMINATED = 'TERMINATED',
    EXPIRED = 'EXPIRED',
    PENDING = 'PENDING',
    CANCELLED = 'CANCELLED',
}
