import {Component, EventEmitter, Input, Output} from "@angular/core";
import {DatePipe} from "@angular/common";
import {HouseDetailResponse} from "../house.model";


@Component({
    selector: "app-house-header",
    template: `
        <div class="ui card">
            <!-- Header -->
            <div class="content">
                <div class="header"><i class="home icon"></i>{{house.name}}</div>
            </div>
            <div class="content">
                <div class="ui small feed">
                    <div class="event">
                        <div class="content">
                            <div class="summary">
                                <p><i class="ui qrcode icon"></i> {{house.houseCode}}</p>
                                <div> <i class="ui map marker alternate icon"></i> {{house.location}}</div>
                                <p>Remark</p>
                                <p>{{house.remark? house.remark : ""}}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="extra content">
                <button class="ui tiny primary tertiary button left floated" (click)="onHouseEdit(house)" style="cursor: pointer">
                  <i class="edit icon"></i>
                  Edit
                </button>
                <button class="ui tiny primary tertiary button right floated" (click)="onAddRoom(house)" style="cursor: pointer">
                  <i class="plus icon"></i>
                  Add Room
                </button>
            </div>
        </div>
    `,
    imports: [
        DatePipe
    ],
    styles: ``
})
export class HouseHeaderComponent{
    @Input({required:true}) house!: HouseDetailResponse;

    @Output() onHouse= new EventEmitter<any>();
    @Output() onRoomAdd= new EventEmitter<any>();
    @Output() edit = new EventEmitter<void>();
    @Output() addRoom = new EventEmitter<void>();
    @Output() delete = new EventEmitter<void>();

    onHouseEdit(data: HouseDetailResponse){
        this.onHouse.emit(data);
    }
    onAddRoom(data: HouseDetailResponse){
        this.onRoomAdd.emit(data);
    }

}
