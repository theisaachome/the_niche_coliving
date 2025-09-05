import {Component, OnInit} from '@angular/core';
import {PlaceholderComponent} from "../../../shared/components/placeholder/placeholder.component";
import {Observable} from "rxjs";
import {Budget} from "../finance.model";
import {BudgetsService} from "../services/budgets.services";
import {DividerComponent} from "../../../shared/components/divider.component";

@Component({
  selector: 'finance-budgets',
    imports: [
        PlaceholderComponent,
        DividerComponent
    ],
  templateUrl: './budgets.component.html',
  styleUrl: './budgets.component.css'
})
export class BudgetsComponent implements OnInit {

    budgets$!: Observable<Budget[]>;

    constructor(private budgetService:BudgetsService) {
    }
    ngOnInit(): void {
        this.budgets$ = this.budgetService.getAllBudgets();
    }

}
