import {Gender} from "./gender.type";
import {BaseModel} from "../../../shared/base.model";


export interface TenantRequest{
    fullName: string;
    phone: string;
    email: string;
    gender: Gender
}

export const TENANT_STATUSES = [
    'ACTIVE',
    'INACTIVE',
    'SUSPENDED'
] as const;

export type TenantStatus = typeof TENANT_STATUSES[number];


export interface TenantResponse {
    id: string;          // UUID
    tenantCode: string;
    fullName: string;
    email: string;
    phone: string;
    status: TenantStatus;
}

export interface TenantDocument {
    id: string;
    type: string;
    referenceNo: string;
}

export interface Address {
    line1: string;
    line2?: string;
    city: string;
    state: string;
    postcode: string;
    country: string;
}

export interface Contact {
    name: string;
    phone: string;
    email?: string;
}

export interface EmergencyContact extends Contact {
    relationship: string;
}


export interface TenantDetail extends BaseModel{
    id: string;                // UUID → string
    tenantCode: string;
    fullName: string;
    email: string;
    phone: string;
    status: TenantStatus;

    documents?: TenantDocument[];
    address?: Address;
    contacts?: Contact[];
    emergencyContacts?: EmergencyContact[];

    deleted: boolean;
}
