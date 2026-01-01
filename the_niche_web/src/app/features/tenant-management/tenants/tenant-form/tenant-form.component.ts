import {Component, EventEmitter, inject, Output} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {InputComponent} from "../../../../shared/components/input/input.component";
import {FuiDropdownComponent} from "../../../../shared/components/dropdown/dropdown-component";
import {FuiSelectComponent} from "../../../../shared/components/select.component";
import {ModalComponent} from "../../../../shared/modal.component";

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
        {label:'Male',value:'Male'},
        {label:'Female',value:'Female'},
        {label:'Other',value:'Other'}
    ]
    isEditMode = false;
    tenantId?: string;

    tenantForm = this.formBuilder.group({
        fullName:['',Validators.required],
        phone:['',Validators.required],
        email:['',Validators.required],
        gender:['',Validators.required]
    })

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
        // this.router.navigate(['/tenants']);
        console.log(this.tenantForm.value)
    }

    submit(): void {
        if (this.tenantForm.invalid) return;

        // API call here
        this.saved.emit();
    }

    onCancel(): void {
        this.cancel.emit();
    }

}
