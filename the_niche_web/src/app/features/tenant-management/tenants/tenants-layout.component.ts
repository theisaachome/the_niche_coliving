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
        <!-- Toolbar -->
        <div class="ui clearing segment">
            <h3 class="ui left floated header">Tenants</h3>
            <button
                    class="ui primary right floated button"
                    (click)="openCreateModal()">
                <i class="plus icon"></i>
                Add Tenant
            </button>
        </div>
        <!-- List of tenants -->
         <router-outlet></router-outlet>
        @if (showCreateModal){
            <app-modal (close)="closeCreateModal()">
                <div modal-title>
                    Create Tenant
                </div>

                <div modal-content>
                    <app-tenant-form
                            (cancel)="closeCreateModal()"
                            (saved)="closeCreateModal()">
                    </app-tenant-form>
                </div>

                <div modal-actions>
                    <!-- Optional: actions can live inside tenant form -->
                </div>
            </app-modal>
        }
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
