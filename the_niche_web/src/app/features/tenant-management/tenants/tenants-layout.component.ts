import {Component} from "@angular/core";
import {RouterOutlet} from "@angular/router";
import {ModalComponent} from "../../../shared/components/modal.component";
import {TenantFormComponent} from "./tenant-form/tenant-form.component";


@Component({
    selector: 'tenant-layout',
    imports: [
        RouterOutlet,
        ModalComponent,
        TenantFormComponent
    ],
    template: `
        <!-- List of tenants -->
         <router-outlet></router-outlet>
    `
})
export class TenantsLayoutComponent{
    showCreateModal = false;

    openCreateModal(): void {
        this.showCreateModal = true;
    }

    closeCreateModal(): void {
        this.showCreateModal = false;
    }
}
