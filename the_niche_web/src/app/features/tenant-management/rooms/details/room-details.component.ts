import {Component, Input, OnInit} from "@angular/core";
import {RoomService} from "../room.service";
import {Room} from "../../house/house.model";
import {ActivatedRoute} from "@angular/router";


@Component({
    selector: "app-room-details",
    templateUrl: "./room-details.component.html",
})
export class RoomDetailsComponent implements OnInit {

   roomDetails?:Room;

   constructor(private roomService: RoomService,
               private route: ActivatedRoute) {
   }

    ngOnInit(): void {
        const roomId = this.route.snapshot.params["id"];
        this.roomService.getRoomDetailsById(roomId)
            .subscribe((res)=>{
                this.roomDetails = res;
            })
    }


}
