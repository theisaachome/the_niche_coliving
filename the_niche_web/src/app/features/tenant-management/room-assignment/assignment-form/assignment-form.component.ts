import {Component, inject} from "@angular/core";
import {InputComponent} from "../../../../shared/components/input/input.component";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {HouseService} from "../../house/house.service";
import {RoomService} from "../../rooms/room.service";
import {TenantsService} from "../../tenants/tenants-service";
import {RoomAssignmentService} from "../room-assignment.service";

@Component({
    selector: "app-assignment-form",
    templateUrl: "./assignment-form.component.html",
    imports: [
        InputComponent,
        ReactiveFormsModule
    ],
    styles: [`

    `]
})
export class AssignmentFormComponent{
    private formBuilder = inject(FormBuilder);

    assignmentForm = this.formBuilder.group({
        tenantId: [null, [Validators.required]],
        houseId: [null, [Validators.required]],
        roomId: [null, [Validators.required]],
        releaseStartDate: [null, [Validators.required]],
        releaseEndDate: [null, [Validators.required]],
        depositAmount: [null, [Validators.required]],
        depositStatus: [null, [Validators.required]],
        notes: [null, [Validators.required]],
    });

    constructor(
        private houseService: HouseService,
        private roomService: RoomService,
        private tenantService:TenantsService,
        private assignmentService:RoomAssignmentService
    ) {
    }


    onSubmit() {

    }
}
