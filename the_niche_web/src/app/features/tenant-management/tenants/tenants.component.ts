import { Component } from '@angular/core';
import {TenantRequest} from "./tenant-modal";
import {TenantsService} from "./tenants-service";
import {FormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";
import {TenantFormComponent} from "./tenant-form/tenant-form.component";

@Component({
  selector: 'app-tenants',
    imports: [
        FormsModule,
        RouterLink,
        NgForOf,
        NgIf,
        TenantFormComponent
    ],
  templateUrl: './tenants.component.html',
  styleUrl: './tenants.component.css',
})
export class TenantsComponent {

    tenants = [
        { id: 1, name: 'John Doe' },
        { id: 2, name: 'Jane Smith' }
    ];

    tenant: TenantRequest = {
        fullName:"",
        phone: "",
        email: "",
        gender: 'MALE'
    }
    isLoading = false;
    errorMessage = "";
    constructor(private tenantService: TenantsService) {
    }
    createTenant() {
        this.isLoading = true;
        this.errorMessage = "";

        this.tenantService.createTenant(this.tenant).subscribe({
            next:(response) =>{
                console.log("Tenant created successfully",response);
                this.isLoading = false;
            },
            error: (err) =>{
                console.error(err);
                this.errorMessage = err.message || "Something went wrong";
                this.isLoading = false;
            }
        })
    }
}
