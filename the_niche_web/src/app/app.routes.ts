import { Routes } from '@angular/router';
import {AccountFormComponent} from './features/account/account-form/account-form.component';
import {NotFoundComponent} from "./shared/components/not-found/not-found.component";
import {DashboardComponent} from "./features/dashboard/dashboard.component";
import {FinanceSummaryComponent} from "./features/finance/summary/finance-summary.component";
import {HouseEditComponent} from "./features/tenant-management/house/house-edit/house-edit.component";
import {TenantResolver} from "./features/tenant-management/tenants/tenant.resolver";
import {
    AssignmentListComponent
} from "./features/tenant-management/room-assignment/assignment-list/assignment-list.component";
import {LayoutComponent} from "./shared/components/layout/layout.component";

export const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            {
                path: '',
                redirectTo: '/dashboard',
                pathMatch: 'full'
            },
            {
                path: 'dashboard',
                loadComponent: () => import('./features/dashboard/dashboard.component').then(c => c.DashboardComponent)
            },
            {
                path: 'assignments',
                loadComponent: () => import('./features/tenant-management/room-assignment/assignment-list/assignment-list.component').then(c => c.AssignmentListComponent)
            },
            {
                path: 'list',
                loadComponent: () => import('./features/list/list.component').then(c => c.ListComponent)
            },
            {
                path: 'table',
                loadComponent: () => import('./features/table/table.component').then(c => c.TableComponent)
            },
            {path:'accounts',component:AccountFormComponent},
            {
                path:'mods',
                loadComponent:()=>import("./features/mods/home/mods-home.component").then((c)=>c.ModsHomeComponent)
            },
            {
                path:'tenants',
                loadComponent:async ()=>{
                    const c = await  import("./features/tenant-management/tenants/tenants-layout.component");
                    return c.TenantsLayoutComponent
                },
                children:[
                    {
                        path:"",
                        loadComponent: () => import("./features/tenant-management/tenants/tenants.component").then(c=>c.TenantsComponent)
                    },
                    {
                        path:"create",
                        loadComponent: () => import("./features/tenant-management/tenants/tenant-form/tenant-form.component").then(c=>c.TenantFormComponent)
                    },
                    {
                        path:"edit/:id",
                        loadComponent: () => import("./features/tenant-management/tenants/tenant-form/tenant-form.component").then(c=>c.TenantFormComponent)
                    },
                    {
                        path:":id",
                        loadComponent: () => import("./features/tenant-management/tenants/tenant-detail/tenant-detail.component").then(c=>c.TenantDetailComponent),
                        resolve: {'tenantData':TenantResolver}
                    }

                ]
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
            {
                path: 'houses',
                loadComponent: async () => {
                    const c = await import("./features/tenant-management/house/houses-layout.component");
                    return c.HousesLayoutComponent;
                },
                children: [
                    {
                        path: '',
                        data: { breadcrumb: 'Houses' },
                        loadComponent: () =>
                            import('./features/tenant-management/house/house-list/house-list.component')
                                .then((c) => c.HouseListComponent),
                    },
                    {
                        path: ":id", // This is the House ID
                        data: { breadcrumb: 'House Details' },
                        loadComponent: () => import('./features/tenant-management/house/details/house-details.component')
                            .then((c) => c.HouseDetailsComponent),
                        children: [
                            {
                                path: "rooms/:roomId", // Deepening to the specific room
                                data: { breadcrumb: 'Room Details' },
                                // If you have a RoomDetailComponent, load it here
                                // Otherwise, you can use a component that acts as a wrapper
                                children: [
                                    {
                                        path: "assignments",
                                        data: { breadcrumb: 'Assignments' },
                                        loadComponent: () =>
                                            import("./features/tenant-management/room-assignment/assignments/assignments.component")
                                                .then(c => c.AssignmentsComponent)
                                    }
                                ]
                            }
                        ]
                    },
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
        ]
    },
    // {
    //     path: '**',
    //     redirectTo: '/dashboard'
    // },
    // {path:'dashboard',component:DashboardComponent},
    // {path:'',redirectTo:'dashboard',pathMatch:'full'},


    // { path: '', redirectTo: 'financials', pathMatch: 'full' }
];
