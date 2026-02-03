import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {FooterComponent} from "./core/footer/footer.component";
import {HeaderComponent} from "./core/header/header.component";
import {FinanceDashboardComponent} from "./features/finance/dashboard/finance-dashboard.component";

@Component({
  selector: 'app-root',
    imports: [RouterOutlet, FooterComponent, HeaderComponent, FinanceDashboardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone:true
})
export class AppComponent {
  title = 'the-niched-coliving';

}
