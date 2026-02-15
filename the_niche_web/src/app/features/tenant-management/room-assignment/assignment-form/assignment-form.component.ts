import {Component, inject} from "@angular/core";
import {InputComponent} from "../../../../shared/components/input/input.component";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {HouseService} from "../../house/house.service";
import {RoomService} from "../../rooms/room.service";
import {TenantsService} from "../../tenants/tenants-service";
import {RoomAssignmentService} from "../room-assignment.service";

declare var $: any; // for jQuery


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

    // need houses
    // rooms of a house

    constructor(
        private houseService: HouseService,
        private roomService: RoomService,
        private tenantService:TenantsService,
        private assignmentService:RoomAssignmentService
    ) {
    }

    onHouseSelectChange(){

    }

    onSubmit() {

    }

    //
    // onCountryChange(event:any){
    //     const selectedCountry = this.counties.find((c)=>c.isoCode===event.target.value);
    //     this.cities=[];
    //
    //     if(selectedCountry){
    //         const countryCities = City.getCitiesOfCountry(selectedCountry.isoCode) ?? [];
    //         this.cities = countryCities;
    //         const cityControl = this.reportForm.get("location.city_name");
    //
    //         if (cityControl) {
    //             cityControl.reset(); // clear previous value
    //             // cityControl.disable(); // keep disabled until check
    //         }
    //
    //
    //         if (cityControl) {
    //             if (this.cities.length > 0) {
    //                 console.log("got cities in this country")
    //                 cityControl.enable();
    //             }
    //         }
    //     }
    // }
}
