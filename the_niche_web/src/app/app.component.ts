import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {TransactionFormComponent} from './features/transactions/transaction-form/transaction-form.component';
import {FooterComponent} from "./core/footer/footer.component";
import {HeaderComponent} from "./core/header/header.component";

@Component({
  selector: 'app-root',
    imports: [RouterOutlet, TransactionFormComponent, FooterComponent, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone:true
})
export class AppComponent {
  title = 'the-niched-coliving';
}
