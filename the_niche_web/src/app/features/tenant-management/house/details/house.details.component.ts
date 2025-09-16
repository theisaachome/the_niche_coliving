import {Component, OnInit} from '@angular/core';
import {HouseService} from "../house.service";
import { HouseDetails} from "../house.model";
import {ActivatedRoute} from "@angular/router";
import {DividerComponent} from "../../../../shared/components/divider.component";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-house.details',
    imports: [
        DividerComponent,
        DatePipe
    ],
  templateUrl: './house.details.component.html',
  styleUrl: './house.details.component.css'
})
export class HouseDetailsComponent implements OnInit {

    house?:HouseDetails;
    constructor(private houseService:HouseService,
                private route:ActivatedRoute) {
    }


    ngOnInit() {
        const id = this.route.snapshot.params['id'];
        this.houseService.getHouseDetailsById(id).subscribe(
            (res)=>{
                console.log(res)
                this.house=res;
            }
        )
    }

}
