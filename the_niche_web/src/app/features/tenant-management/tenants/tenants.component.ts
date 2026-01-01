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

@Component({
  selector: 'app-tenants',
    imports: [
        FormsModule,
        RouterLink,
        AsyncPipe,
        TenantStatusBadgeComponent,
        PaginationComponent
    ],
  templateUrl: './tenants.component.html',
  styleUrl: './tenants.component.css',
})
export class TenantsComponent implements OnInit{

    tenant$:Observable<TenantResponse[]> | undefined;
    meta?: Omit<PageResponse<any>,'content'>;
    pageNo = 0;
    pageSize = 2;

    constructor(private tenantService: TenantsService) {

    }

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
}
