import {Injectable} from "@angular/core";
import {HOUSE_API, ROOM_API} from "../../../shared/api-constants";
import {Observable} from "rxjs";
import {CrudInterface} from "../../../shared/service/crud.interface";
import {HttpClient} from "@angular/common/http";
import {RoomRequest, RoomResponse, RoomUpdateRequest} from "./room.model";


@Injectable({providedIn: 'root'})
export class RoomService implements CrudInterface<any>{

    constructor(private http:HttpClient) {
    }
    getAll(): Observable<any[]> {
        throw new Error("Method not implemented.");
    }
    getById(id: string | number): Observable<any> {
        throw new Error("Method not implemented.");
    }
    delete(id: string | number): Observable<void> {
        throw new Error("Method not implemented.");
    }
    getRoomsByHouse(id:string):Observable<RoomResponse[]>{
        return this.http.get<RoomResponse[]>(`${HOUSE_API}`+`/${id}/rooms`);
    }
    saveRoom(houseId:string,roomRequest:RoomRequest):Observable<RoomResponse>{
        const url = `${HOUSE_API}/${houseId}/rooms`;
        return this.http.post<RoomResponse>(url,roomRequest);
    }
    updateRoom(houseId:string,roomUpdateRequest:RoomUpdateRequest) :Observable<RoomResponse> {
        const url = `${HOUSE_API}/${houseId}/rooms/${roomUpdateRequest.id}`;
        return this.http.put<RoomResponse>(url,roomUpdateRequest);
    }
}

