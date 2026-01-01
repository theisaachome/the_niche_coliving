
import { Component, Input } from '@angular/core';
import {TenantStatus} from "../../features/tenant-management/tenants/tenant-modal";

@Component({
    selector: 'app-tenant-status-badge',
    template:`
        <span class="ui {{ cssClass }} label">
          {{ label }}
        </span>
    `
})
export class TenantStatusBadgeComponent {
    @Input({ required: true }) status!: TenantStatus;

    get cssClass(): string {
        switch (this.status) {
            case 'ACTIVE':
                return 'green';
            case 'INACTIVE':
                return 'grey';
            case 'SUSPENDED':
                return 'red';
            default:
                return '';
        }
    }

    get label(): string {
        return this.status.charAt(0) + this.status.slice(1).toLowerCase();
    }
}
//
//
// <tr *ngFor="let tenant of tenants">
//     <td>{{ tenant.tenantCode }}</td>
// <td>{{ tenant.fullName }}</td>
// <td>{{ tenant.email }}</td>
// <td>{{ tenant.phone }}</td>
// <td>
// <app-tenant-status-badge
//     [status]="tenant.status">
//     </app-tenant-status-badge>
//     </td>
//     </tr>
