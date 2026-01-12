import {Component, OnInit} from '@angular/core';
import {HouseService} from "../house.service";
import {HouseDetailResponse, HouseDetails, HouseResponse} from "../house.model";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {DividerComponent} from "../../../../shared/components/divider.component";
import {AsyncPipe, DatePipe, JsonPipe, NgClass, NgIf} from "@angular/common";
import {RoomResponse} from "../../rooms/room.model";
import {Observable} from "rxjs";
import {RoomService} from "../../rooms/room.service";
import {ModalComponent} from "../../../../shared/components/modal.component";
import {HouseEditComponent} from "../house-edit/house-edit.component";

@Component({
  selector: 'app-house-details',
    imports: [
        DividerComponent,
        AsyncPipe,
        DatePipe,
        RouterLink,
        NgClass,
        JsonPipe,
        NgIf,
        ModalComponent,
        HouseEditComponent,
    ],
  templateUrl: './house-details.component.html',
  styleUrl: './house-details.component.css'
})
export class HouseDetailsComponent implements OnInit {

    house?:HouseDetailResponse;
    rooms$: Observable< RoomResponse[]> | undefined;
    modalOpen = false;


    constructor(private houseService:HouseService,
                private roomService:RoomService,
                private route:ActivatedRoute,
                private router:Router) {
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

    onHouseEdit(house:HouseDetailResponse){
        console.log(house)
        this.modalOpen = true;
    }
    onSubmit(){
        this.modalOpen = false;
        console.log("Modal closed");
    }

}
