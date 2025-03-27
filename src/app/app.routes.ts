import { Routes } from '@angular/router';
import {AccountFormComponent} from './features/account/account-form/account-form.component';
import {TransactionListComponent} from './features/transactions/transaction-list/transaction-list.component';
import {UnitsListComponent} from './features/units/units-list/units-list.component';

export const routes: Routes = [
  {path:'',component:TransactionListComponent},
  {path:'transactions',component:TransactionListComponent},
    {path:'accounts',component:AccountFormComponent},
  {path:'units',component:UnitsListComponent}
];
