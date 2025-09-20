import { Routes } from '@angular/router';
import {AccountFormComponent} from './features/account/account-form/account-form.component';
import {NotFoundComponent} from "./shared/components/not-found/not-found.component";
import {DashboardComponent} from "./features/dashboard/dashboard.component";
import {FinanceSummaryComponent} from "./features/finance/summary/finance-summary.component";
import {HouseFormComponent} from "./features/tenant-management/house/house-form/house-form.component";

export const routes: Routes = [
  {path:'dashboard',component:DashboardComponent},
    {path:'',redirectTo:'dashboard',pathMatch:'full'},
    {path:'accounts',component:AccountFormComponent},
    {
        path:'mods',
        loadComponent:()=>import("./features/mods/home/mods-home.component").then((c)=>c.ModsHomeComponent)
    },
    {
        path:'views',
        loadComponent:()=>import("./features/views/views-home.component").then((c)=>c.ViewsHomeComponent),
        children:[
            {path:'',loadComponent:()=>import("./features/views/statistics/statistics.component").then((c)=>c.StatisticsComponent)}
        ]
    },
    {
        path:'collections',
        loadComponent:()=>import("./features/collections/collection-home/collection-home.component").then((c)=>c.CollectionHomeComponent),
        children:[
            {
                path: '',
                loadComponent:()=>import("./features/collections/biography/biography.component").then((c)=>c.BiographyComponent)
            },
            {
                path: 'companies',
                loadComponent:()=>import("./features/collections/companies/companies.component").then((c)=>c.CompaniesComponent)
            },
            {
                path: 'partners',
                loadComponent:()=>import("./features/collections/partners/partners.component").then((c)=>c.PartnersComponent)
            }
        ]
    },

    {path:'houses',
    loadComponent:async () => {
        const c = await import("./features/tenant-management/house/houses.component");
        return c.HousesComponent;
    },
      children:[
          {
              path: '', // default child when visiting /houses
              loadComponent: () =>
                  import('./features/tenant-management/house/house-list/house-list.component')
                      .then((c) => c.HouseListComponent),
          },
          {
              path:'new',
              loadComponent:()=>import('./features/tenant-management/house/house-form/house-form.component')
                      .then((c)=>c.HouseFormComponent),
          },
          {
              path:":id",
              loadComponent:()=>import('./features/tenant-management/house/details/house.details.component')
                  .then((c)=>c.HouseDetailsComponent),
          }
      ]
    },
    {
        path:'rooms',
        loadComponent:async ()=>{
            const c = await  import("./features/tenant-management/rooms/rooms.component");
            return c.RoomsComponent;
        },
        children:[
            {
                path: '',
                loadComponent:()=> import("./features/tenant-management/rooms/room-list/room-list.component").then(c=>c.RoomListComponent),
            },
            {
                path: ':id',
                loadComponent:()=> import("./features/tenant-management/rooms/details/room-details.component").then(c=>c.RoomDetailsComponent),
            }
        ]
    },
    {   path:'finances',
        loadComponent:async () => {
            const c = await import("./features/finance/dashboard/finance-dashboard.component");
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
                    import('./features/finance/budgets/budgets.component')
                        .then(c => c.BudgetsComponent)
            },
            {
                path: 'transactions',
                loadComponent: () =>
                    import('./features/finance/transactions/transaction-list/transaction-list.component')
                        .then(c => c.TransactionListComponent)
            },

        ]
    },
    {path:'**',component:NotFoundComponent}
    // { path: '', redirectTo: 'financials', pathMatch: 'full' }
];
