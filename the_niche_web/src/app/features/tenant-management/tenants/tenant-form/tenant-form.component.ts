import { Component } from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-tenant-form',
    imports: [
        ReactiveFormsModule,
        FormsModule,
        NgIf
    ],
  templateUrl: './tenant-form.component.html',
  styleUrl: './tenant-form.component.css',
})
export class TenantFormComponent {


    isEditMode = false;
    tenantId?: string;

    constructor(private route: ActivatedRoute, private router: Router) {
        this.route.paramMap.subscribe(params => {
            this.tenantId = params.get('id') || undefined;
            this.isEditMode = !!this.tenantId;

            if (this.isEditMode) {
                // Load tenant data from API
                // Example: this.api.getTenant(this.tenantId).subscribe(data => this.form.patchValue(data));
            }
        });
    }

    onSubmit() {
        /*
        if (this.isEditMode) {
            // Call update API
            console.log('Update tenant', this.form.value);
        } else {
            // Call create API
            console.log('Create tenant', this.form.value);
        }

         */
        this.router.navigate(['/tenants']);
    }

}
