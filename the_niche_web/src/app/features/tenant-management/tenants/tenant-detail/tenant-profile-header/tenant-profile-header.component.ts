import {Component, Input} from '@angular/core';
import {TenantDetail} from "../../tenant-modal";

@Component({
  selector: 'app-tenant-profile-header',
  imports: [],
  templateUrl: './tenant-profile-header.component.html',
  styleUrl: './tenant-profile-header.component.css',
})
export class TenantProfileHeaderComponent {
   @Input() tenantProfile: TenantDetail | undefined;

   constructor() {
   }

}
