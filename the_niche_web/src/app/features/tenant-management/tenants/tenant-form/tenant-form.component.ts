import {Component, EventEmitter, inject, Output} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {InputComponent} from "../../../../shared/components/input/input.component";
import {FuiDropdownComponent} from "../../../../shared/components/dropdown/dropdown-component";
import {FuiSelectComponent} from "../../../../shared/components/select.component";
import {ModalComponent} from "../../../../shared/components/modal.component";
import {TenantsService} from "../tenants-service";
import {TenantRequest} from "../tenant-modal";
import {Gender} from "../gender.type";

@Component({
  selector: 'app-tenant-form',
    imports: [
        ReactiveFormsModule,
        FormsModule,
        InputComponent,
        FuiDropdownComponent,
        FuiSelectComponent,
        ModalComponent,
    ],
  templateUrl: './tenant-form.component.html',
  styleUrl: './tenant-form.component.css',
})
export class TenantFormComponent {



    @Output() cancel = new EventEmitter<void>();
    @Output() saved = new EventEmitter<void>();

    private formBuilder = inject(FormBuilder);
    genders= [
        {label:'Male',value:'MALE'},
        {label:'Female',value:'FEMALE'}
    ]
    isEditMode = false;
    tenantId?: string;
    isLoading = false;
    errorMessage = "";


    tenantForm = this.formBuilder.nonNullable.group({
        fullName:['',Validators.required],
        phone:['',Validators.required],
        email:['',[Validators.required,Validators.email]],
        gender:['MALE' as Gender,Validators.required]
    })

    constructor(private tenantService:TenantsService,private route: ActivatedRoute, private router: Router) {
        this.route.paramMap.subscribe(params => {
            this.tenantId = params.get('id') || undefined;
            this.isEditMode = !!this.tenantId;

            if (this.isEditMode) {
                // Load tenant data from API
                // Example: this.api.getTenant(this.tenantId).subscribe(data => this.form.patchValue(data));
            }
        });
    }

    submit(): void {
        if (this.tenantForm.invalid) return;
        const tenantRequest: TenantRequest = this.tenantForm.getRawValue();
        if (this.isEditMode) {
            // Call update API
            console.log("Update tenant",this.tenantForm.value);
        }else {
            // Call create API
            console.log('Create tenant', this.tenantForm.value);
            this.tenantService.createTenant(tenantRequest)
                .subscribe({
                    next:(response)=>{
                        console.log("Tenant created successfully",response);
                        this.isLoading = false;
                    },
                    error:(err)=>{
                        console.error(err);
                        this.errorMessage = err.message || "Something went wrong";
                        this.isLoading = false;
                    }
                })
        }
        this.saved.emit();
    }

    onCancel(): void {
        this.cancel.emit();
    }

}
