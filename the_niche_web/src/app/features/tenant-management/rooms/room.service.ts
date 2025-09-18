import {BaseService} from "../../../shared/service/base.service";
import {Room} from "../house/house.model";
import {Injectable} from "@angular/core";
import {ROOM_API} from "../../../shared/api-constants";
import {Observable} from "rxjs";


@Injectable({providedIn: 'root'})
export class RoomService extends BaseService<Room>{
    protected override url(): string {
        return ROOM_API;
    }

    getRoomDetailsById(id:string):Observable<Room>{
        return this.http.get<Room>(ROOM_API+'/'+id)
    }
}
