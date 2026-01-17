import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Room} from "../../house/house.model";
import {RoomResponse} from "../room.model";
import {AsyncPipe, NgClass} from "@angular/common";

@Component({
    selector: 'room-list',
    templateUrl: './room-list.component.html',
    imports: [
        AsyncPipe,
        NgClass
    ]
})
export class RoomListComponent{
    @Input() rooms!: RoomResponse[];
    @Input() houseName!: string;
    @Input() houseId!: string;

    @Output() editRoom = new EventEmitter<Room>();
    @Output() assignTenant = new EventEmitter<Room>();

}
