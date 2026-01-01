import {Gender} from "./gender.type";


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
