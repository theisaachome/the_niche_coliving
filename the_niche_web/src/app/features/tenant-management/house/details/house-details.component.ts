import {AfterViewInit, Component, OnInit} from '@angular/core';
import {HouseService} from "../house.service";
import {
    HOUSE_STATUS,
    HOUSE_STATUS_META,
    HouseDetailResponse,
    HouseDetails,
    HouseResponse,
    HouseStatus
} from "../house.model";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {DividerComponent} from "../../../../shared/components/divider.component";
import {AsyncPipe, DatePipe, JsonPipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {RoomResponse} from "../../rooms/room.model";
import {Observable} from "rxjs";
import {RoomService} from "../../rooms/room.service";
import {ModalComponent} from "../../../../shared/components/modal.component";
import {HouseEditComponent} from "../house-edit/house-edit.component";
import {FormsModule} from "@angular/forms";


declare var $: any;
@Component({
  selector: 'app-house-details',
    imports: [
        AsyncPipe,
        DatePipe,
        RouterLink,
        NgClass,
        ModalComponent,
        HouseEditComponent,
        FormsModule,
    ],
  templateUrl: './house-details.component.html',
  styleUrl: './house-details.component.css'
})
export class HouseDetailsComponent implements OnInit,AfterViewInit {

    house?:HouseDetailResponse;
    rooms$: Observable< RoomResponse[]> | undefined;
    modalOpen = false;

    selectedStatus?: string;
    houseStatuses = HOUSE_STATUS.map(status => ({
        value: status,
        label: HOUSE_STATUS_META[status].label,
        icon: HOUSE_STATUS_META[status].icon
    }));

    constructor(private houseService:HouseService,
                private roomService:RoomService,
                private route:ActivatedRoute,
                private router:Router) {
    }

    ngAfterViewInit(): void {
        $('ui.dropdown').dropdown({});
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

    archiveHouse(house:any){
        var data = {
            houseId:house.id,
            name:house.name,
        }
        this.houseService.archiveHouse(data)
            .subscribe({
                next:(res)=>{
                    console.log(res)
                },
                error: (err) => {}
            });

    }
    unarchiveHouse(){

    }
    getHouseStatusIcon(status: HouseStatus): string {
        return HOUSE_STATUS_META[status].icon;
    }
    getHouseStatusLabel(status: HouseStatus): string {
        return HOUSE_STATUS_META[status].label;
    }


}
