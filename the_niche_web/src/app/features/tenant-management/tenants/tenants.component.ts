import {Component, OnInit} from '@angular/core';
import {TenantsService} from "./tenants-service";
import {FormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {map, Observable, tap} from "rxjs";
import {TenantResponse} from "./tenant-modal";
import {PageResponse} from "../../../shared/base.model";
import {AsyncPipe} from "@angular/common";
import {TenantStatusBadgeComponent} from "../../../shared/components/tenant-status-badge.component";
import {PaginationComponent} from "../../../shared/components/pagination.component";
import {DividerComponent} from "../../../shared/components/divider.component";
import {ModalComponent} from "../../../shared/components/modal.component";
import {TenantFormComponent} from "./tenant-form/tenant-form.component";

@Component({
  selector: 'app-tenants',
    imports: [
        FormsModule,
        AsyncPipe,
        TenantStatusBadgeComponent,
        PaginationComponent,
        RouterLink,
        ModalComponent,
        TenantFormComponent
    ],
  templateUrl: './tenants.component.html',
  styleUrl: './tenants.component.css',
})
export class TenantsComponent implements OnInit{

    tenant$:Observable<TenantResponse[]> | undefined;
    meta?: Omit<PageResponse<any>,'content'>;
    pageNo = 0;
    pageSize = 5;

    modalState = {
        openCreateTenant:false
    }

    constructor(private tenantService: TenantsService,) {}

    ngOnInit(): void {
        this.loadTenants(this.pageNo,this.pageSize);
    }

    loadTenants(page:number,size:number){
        this.tenant$ = this.tenantService.getTenantsPage(page,size)
            .pipe(
                tap((res:PageResponse<TenantResponse>)=>{
                    this.meta = {
                        pageNo: res.pageNo,
                        pageSize: res.pageSize,
                        totalElement: res.totalElement,
                        totalPage: res.totalPage,
                        last: res.last
                    }
                }),
                map(res => res.content)
            )
    }

    onPageChange(newPage:number){
        this.loadTenants(newPage,this.pageSize);
    }
    onPageSizeChange(newSize: number) {
        this.pageNo = 0; // reset to first page
        this.loadTenants(0, newSize);
    }
    viewDetail(){

    }

    trackByTenantId(index: number, tenant: TenantResponse) {
        return tenant?.id ?? index;
    }
    openCreateTenantModal() {
        this.modalState.openCreateTenant = true;
        console.log(this.modalState.openCreateTenant)
    }
    closeCreatTenantModal(): void {
       this.modalState.openCreateTenant = false;
    }

}
