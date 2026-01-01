

export interface BaseModel{
    id:number|string;
    createdBy: string;
    updatedBy: string;
    createdDate: string; // ISO 8601 string
    updatedDate: string;
}


export interface PageResponse<T> {
    content: T[];
    pageNo: number;
    pageSize: number;
    totalElement: number;
    totalPage: number;
    last: boolean;
}
