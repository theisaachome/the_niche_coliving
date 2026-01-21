import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Room} from "../../house/house.model";
import {RoomResponse} from "../room.model";
import {AsyncPipe, NgClass} from "@angular/common";
import {Observable} from "rxjs";
import {ModalComponent} from "../../../../shared/components/modal.component";
import {RoomFormComponent} from "../room-form/room-form.component";

@Component({
    selector: 'room-list',
    templateUrl: './room-list.component.html',
    imports: [
        AsyncPipe,
        NgClass,
        ModalComponent,
        RoomFormComponent
    ]
})
export class RoomListComponent{
    @Input() houseId!: string;
    @Input({ required: true }) rooms$!: Observable<RoomResponse[]>;
    @Input({ required: true }) houseName!: string;
    @Output() editRoom = new EventEmitter<Room>();
    @Output() assignTenant = new EventEmitter<Room>();

    selectedRoom:any;

    modalConfig = {
        openEditRoomModal: false,
    };

    onEditRoom(room:any){
        this.modalConfig.openEditRoomModal = true;
        this.selectedRoom = room;
    }

    closeAllModals() {
        this.modalConfig.openEditRoomModal = false;
    }

}
