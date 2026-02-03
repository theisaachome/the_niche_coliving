import {BaseModel} from "../../../shared/base.model";


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
