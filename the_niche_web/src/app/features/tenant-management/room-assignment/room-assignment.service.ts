import {Injectable} from "@angular/core";
import {CrudInterface} from "../../../shared/service/crud.interface";
import {map, Observable, shareReplay} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ROOM_ASSIGNMENT} from "../../../shared/api-constants";
import {RoomAssignmentDetailsResponse, RoomAssignmentListResponse} from "./room-assignment.model";
import {PageResponse} from "../../../shared/base.model";


@Injectable(
    {providedIn: "root"}
)
export class RoomAssignmentService implements CrudInterface<any> {
    constructor(private http: HttpClient) { }
    getAll(): Observable<any[]> {
        throw new Error("Method not implemented.");
    }
    getById(id: string | number): Observable<any> {
        throw new Error("Method not implemented.");
    }
    delete(id: string | number): Observable<void> {
        throw new Error("Method not implemented.");
    }
    getRoomAssignmentByRoomId(roomId:string):Observable<any>{
        const url = `${ROOM_ASSIGNMENT}/rooms/${roomId}`;
        return this.http.get(url);
    }

    getRoomAssignmentDetailsById(roomAssignmentId:string) :Observable<RoomAssignmentDetailsResponse> {
        const url = `${ROOM_ASSIGNMENT}/${roomAssignmentId}`;
        return this.http.get<RoomAssignmentDetailsResponse>(url);
    }

    loadAllRoomAssignments(
        page = 0,
        size = 20
    ): Observable<PageResponse<RoomAssignmentListResponse>> {
        const url = `${ROOM_ASSIGNMENT}?page=${page}&size=${size}`;
        return this.http
            .get<PageResponse<RoomAssignmentListResponse>>(url)
            .pipe(shareReplay(1));
    }


}
