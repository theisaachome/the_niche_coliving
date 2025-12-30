import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-tenant-details',
  imports: [],
  templateUrl: './tenant-details.component.html',
  styleUrl: './tenant-details.component.css',
})
export class TenantDetailsComponent {
    tenant: any;

    constructor(private route: ActivatedRoute) {
        const id = this.route.snapshot.paramMap.get('id');
        // Fetch tenant by id
        this.tenant = { id, name: 'John Doe' }; // Example
    }

}
