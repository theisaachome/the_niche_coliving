import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";

@Component({
  selector: 'finance-dashboard',
    standalone:true,
    imports: [
        RouterLink,
        RouterOutlet,
        RouterLinkActive
    ],
  templateUrl: './finance-dashboard.component.html',
})
export class FinanceDashboardComponent {

}
