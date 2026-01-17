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
import {RoomFormComponent} from "../../rooms/room-form/room-form.component";
import {HouseHeaderComponent} from "./house-header.component";
import {RoomListComponent} from "../../rooms/room-list/room-list.component";


declare var $: any;
@Component({
  selector: 'app-house-details',
    imports: [
        AsyncPipe,
        ModalComponent,
        HouseEditComponent,
        FormsModule,
        RoomFormComponent,
        HouseHeaderComponent,
        RoomListComponent,
    ],
  templateUrl: './house-details.component.html',
  styleUrl: './house-details.component.css'
})
export class HouseDetailsComponent implements OnInit,AfterViewInit {

    house!:HouseDetailResponse;
    rooms$!: Observable< RoomResponse[]>;

    selectedRoom: any | null = null;
    // Modal states
    modalStates = {
        houseEdit: false,
        houseDelete: false,
        roomForm: false,
        roomEdit: false
    };

    modalOpen = false;
    roomForm = true;
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
        $('.ui.selection.dropdown').dropdown();
    }


    ngOnInit() {
        this.loadHouseDetails();
    }
    private loadHouseDetails():void {
        const houseId = this.route.snapshot.paramMap.get('id')!;
        this.houseService.getById(houseId).subscribe(
            (res)=>{
                this.house=res;
                this.roomService.getRoomsByHouse(houseId).subscribe()
            }
        );
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
    getHouseStatusIcon(status: HouseStatus): string {
        return HOUSE_STATUS_META[status].icon;
    }
    getHouseStatusLabel(status: HouseStatus): string {
        return HOUSE_STATUS_META[status].label;
    }

    deleteHouse(house:HouseDetailResponse){
        this.houseService.delete(house.id).subscribe({
            next:(res)=>{
                console.log(res);
            },
            error: (err) => {},
            complete:()=>{

            }
        })
    }

    onRoomAdded(){
        this.modalStates.roomForm = true;
        console.log("Modal addRoomAdded");
    }

    // Modal handlers
    openHouseEdit(): void {
        this.modalStates.houseEdit = true;
    }

    openRoomForm(): void {
        this.modalStates.roomForm = true;
    }

    openRoomEdit(room: any): void {
        this.selectedRoom = room;
        this.modalStates.roomEdit = true;
    }
    openDeleteDialog(): void {
        this.modalStates.houseDelete = true;
    }

    closeAllModals(): void {
        this.modalStates = {
            houseEdit: false,
            houseDelete: false,
            roomForm: false,
            roomEdit: false
        };
        this.selectedRoom = null;
    }




}
