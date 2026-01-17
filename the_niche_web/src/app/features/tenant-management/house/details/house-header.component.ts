import {Component, EventEmitter, Input, Output} from "@angular/core";
import {DatePipe} from "@angular/common";
import {HouseDetailResponse} from "../house.model";


@Component({
    selector: "app-house-header",
    template: `
        <div class="ui fluid card">
            <!-- Header -->
            <div class="content">
                <div class="ui grid">
                    <div class="twelve wide column">
                        <h2 class="ui header">
                            <i class="home icon"></i>
                            <div class="content">
                                {{ house.name }}
                                <div class="sub header">
                                    {{ house.houseCode }} • {{ house.location }}
                                </div>
                            </div>
                        </h2>
                        <div class="subheader">
                            <p class="ui small grey text">Status
                                <i class="ui check circle green icon "></i>
                                {{ house.status }}</p>
                        </div>
                    </div>

                    <!-- Actions -->
                    <div class="four wide column right aligned">
                        <div class="ui small buttons">
                            <button class="ui primary button" (click)="onHouseEdit(house)">
                                <i class="edit icon"></i>
                                Edit
                            </button>
                            <div class="or"></div>
                            <button class="ui green button" (click)="onAddRoom(house)">
                                <i class="plus icon"></i>
                                Add Room
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Main Details -->
            <div class="content">
                <div class="ui two column grid">
                    <div class="column">
                        <h4 class="ui header">Remark</h4>
                        <p>{{ house.remark || 'No notes available.' }}</p>
                    </div>
                </div>

            </div>

            <!-- Footer / Meta -->
            <div class="extra content">
                <div class="ui small grey text">
                    <i class="user icon"></i> <i>Created by {{ house.createdBy }}
                    on {{ house.createdDate | date:'dd mm yyyy hh:mm' }}</i><br/>
                    <i class="edit icon"></i> <small>Last updated by {{ house.updatedBy }}
                    on {{ house.updatedDate | date: 'dd mm yyyy hh:mm' }} </small>
                </div>
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

    onHouseEdit(data: HouseDetailResponse){
        this.onHouse.emit(data);
    }
    onAddRoom(data: HouseDetailResponse){
        this.onRoomAdd.emit(data);
    }

}
