import {Component} from "@angular/core";
import {RouterOutlet} from "@angular/router";


@Component({
    selector: 'tenant-layout',
    imports: [
        RouterOutlet
    ],
    template: `
        <div class="tenant-layout">
            <h1>Tenants</h1>
            <button routerLink="create">Create Tenant</button>
            <router-outlet></router-outlet>
        </div>

    `
})
export class TenantsLayoutComponent{}
