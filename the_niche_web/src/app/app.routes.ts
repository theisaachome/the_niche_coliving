import { Routes } from '@angular/router';
import {AccountFormComponent} from './features/account/account-form/account-form.component';
import {TransactionListComponent} from './features/financials/transactions/transaction-list/transaction-list.component';
import {UnitsListComponent} from './features/tenant-management/units/units-list/units-list.component';
import {FinanceDashboardComponent} from "./features/financials/dashboard/finance-dashboard.component";
import {BudgetType} from "@angular/build/private";
import {BudgetsComponent} from "./features/financials/budgets/budgets.component";
import {NotFoundComponent} from "./shared/components/not-found/not-found.component";
import {DashboardComponent} from "./features/dashboard/dashboard.component";
import {FinanceSummaryComponent} from "./features/financials/summary/finance-summary.component";

export const routes: Routes = [
  {path:'dashboard',component:DashboardComponent},
    {path:'',redirectTo:'dashboard',pathMatch:'full'},
    {path:'accounts',component:AccountFormComponent},
  {path:'units',
    loadComponent:async () => {
        const c = await import("./features/tenant-management/units/units-list/units-list.component");
        return c.UnitsListComponent;
    }
  },
    {   path:'finances',
        loadComponent:async () => {
            const c = await import("./features/financials/dashboard/finance-dashboard.component");
            return c.FinanceDashboardComponent;
        },
        children:[
            {
                path:'',component:FinanceSummaryComponent,
            },
            {
                path: 'summary',
                redirectTo: 'finances',
                pathMatch: 'full'
            },
            {
                path: 'budgets',
                loadComponent: () =>
                    import('./features/financials/budgets/budgets.component')
                        .then(c => c.BudgetsComponent)
            },
            {
                path: 'transactions',
                loadComponent: () =>
                    import('./features/financials/transactions/transaction-list/transaction-list.component')
                        .then(c => c.TransactionListComponent)
            },

        ]
    },
    {path:'**',component:NotFoundComponent}
    // { path: '', redirectTo: 'financials', pathMatch: 'full' }
];
