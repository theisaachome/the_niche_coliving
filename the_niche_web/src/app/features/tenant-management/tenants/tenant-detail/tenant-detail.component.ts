import {Component, inject} from '@angular/core';
import {ActivatedRoute, RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {TenantDetail} from "../tenant-modal";
import {AvatarHeaderComponent} from "../../../../shared/components/avatar-header/avatar-header.component";
import {DividerComponent} from "../../../../shared/components/divider.component";
import {InfoItem} from "../../../../shared/components/info-list/info-item.model";
import {InfoListComponent} from "../../../../shared/components/info-list/info-list.component";

@Component({
  selector: 'app-tenant-details',
    imports: [
        AvatarHeaderComponent,
        DividerComponent,
        InfoListComponent,
        RouterLink,
        RouterLinkActive,
        RouterOutlet
    ],
  templateUrl: './tenant-detail.component.html',
  styleUrl: './tenant-detail.component.css',
})
export class TenantDetailComponent {
    private route = inject(ActivatedRoute);
    tenant: TenantDetail;
    fullAddress="";


    constructor() {
        // access via snapshot ( for one-time load)
        this.tenant = this.route.snapshot.data['tenantData'];

        // OR subscribe to data (if the route can change without re-instantiation)
        // this.route.data.subscribe(data => {
        //     this.tenant = data['tenantData'];
        // });
    }

    get aboutItems(): InfoItem[]{

        if (!this.tenant) return [];
      return [
              {
                id:1,
                    icon: 'phone icon',
                label: 'Phone',
                value: this.tenant.phone? this.tenant.phone : '—'
             },
            {
                id:2,
                    icon: 'email icon',
                label: 'Email',
                value: this.tenant.email??'—'
            }
        ]
    }

    get aboutAddress(): InfoItem[] {
        if (!this.tenant) return [];
        return [
            {
                id:1,
                icon: 'address book icon',
                label: 'Address',
                value: this.tenant.address? this.fullAddress : '—'
            },
            {
                id:2,
                icon: 'map marker alternate icon',
                label: 'Postcode',
                value: this.tenant.address?.postcode ?? '—'
            },
            {
                id:3,
                icon: 'phone icon',
                label:'City state',
                value: this.tenant.phone
            },
            {
                id:4,
                icon: 'map marker alternate icon',
                label: 'Country',
                value: "Myanmar"
            }
        ];
    }

    get aboutTenantDetails(): InfoItem[]{
        if (!this.tenant) return [];
        return [
            {
                id:1,
                icon: 'calendar outline icon',
                label: 'Date of birth',
                value: "Sep 26, 1988"
            },
            {
                id:2,
                icon: 'address card outline icon',
                label: 'National ID',
                value: "GER0989877"
            },
            {
                id:3,
                icon: 'briefcase icon',
                label:'Title',
                value: "Student"
            },
            {
                id:4,
                icon: 'calendar outline icon',
                label: 'Move in',
                value: "Jan 20, 2025"
            }
        ];
    }
}
