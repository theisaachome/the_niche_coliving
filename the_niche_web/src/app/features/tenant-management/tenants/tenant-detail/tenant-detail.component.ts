import {Component, inject} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {TenantDetail} from "../tenant-modal";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-tenant-details',
    imports: [
        RouterLink,
        NgClass
    ],
  templateUrl: './tenant-detail.component.html',
  styleUrl: './tenant-detail.component.css',
})
export class TenantDetailComponent {
    private route = inject(ActivatedRoute);
    tenant: TenantDetail;

    constructor() {
        // access via snapshot ( for one-time load)
        this.tenant = this.route.snapshot.data['tenantData'];

        // OR subscribe to data (if the route can change without re-instantiation)
        // this.route.data.subscribe(data => {
        //     this.tenant = data['tenantData'];
        // });
    }

    sampleData (){
       const address ={
            line1:" street line1,",
           line2: " line2 street",
           city:"KL",
           state:"KL",
           postcode:"56100",
           country:"Malaysia"
        }
        const address2 ={
            line1:" street line1,",
            line2: " line2 street",
            city:"KL",
            state:"KL",
            postcode:"56100",
            country:"Malaysia"
        }
    }

}
