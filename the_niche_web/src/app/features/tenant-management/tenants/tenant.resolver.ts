import {inject, Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {TenantDetail} from "./tenant-modal";
import {TenantsService} from "./tenants-service";

@Injectable({ providedIn: 'root' })
export class TenantResolver implements Resolve<TenantDetail> {

    private tenantsService = inject(TenantsService);

    resolve(route: ActivatedRouteSnapshot) {
        return this.tenantsService.getTenantById(route.paramMap.get('id')!);
    }
}
