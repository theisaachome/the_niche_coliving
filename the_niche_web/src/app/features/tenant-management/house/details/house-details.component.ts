import {Component, OnInit} from '@angular/core';
import {HouseService} from "../house.service";
import {HouseDetailResponse, HouseDetails, HouseResponse} from "../house.model";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {DividerComponent} from "../../../../shared/components/divider.component";
import {AsyncPipe, DatePipe} from "@angular/common";
import {RoomResponse} from "../../rooms/room.model";
import {Observable} from "rxjs";
import {RoomService} from "../../rooms/room.service";

@Component({
  selector: 'app-house-details',
    imports: [
        DividerComponent,
        AsyncPipe,
        DatePipe,
        RouterLink,
    ],
  templateUrl: './house-details.component.html',
  styleUrl: './house-details.component.css'
})
export class HouseDetailsComponent implements OnInit {

    house?:HouseDetailResponse;
    rooms$: Observable< RoomResponse[]> | undefined;

    constructor(private houseService:HouseService,
                private roomService:RoomService,
                private route:ActivatedRoute) {
    }


    ngOnInit() {
        const id = this.route.snapshot.params['id'];
        this.houseService.getById(id).subscribe(
            (res)=>{
                console.log(res)
                this.house=res;
            }
        );
        this.getRoom(id);
    }

    getRoom(houseId:string){
       this.rooms$ =  this.roomService.getRoomsByHouse(houseId);
    }

}
