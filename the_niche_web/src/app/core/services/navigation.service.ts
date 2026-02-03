
import { Injectable, signal, computed, inject } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem, UserProfile, DashboardStats } from '../models/menu-item.model';

@Injectable({
    providedIn: 'root'
})
export class NavigationService {
    private router = inject(Router);

    // Signals for reactive state management
    private _sidebarCollapsed = signal(false);
    private _currentRoute = signal('');
    private _userProfile = signal<UserProfile>({
        name: 'John Doe',
        email: 'john.doe@example.com',
        role: 'Administrator'
    });

    // Computed signals
    sidebarCollapsed = this._sidebarCollapsed.asReadonly();
    currentRoute = this._currentRoute.asReadonly();
    userProfile = this._userProfile.asReadonly();

    private readonly menuItems: MenuItem[] = [
        {
            id: 'dashboard',
            title: 'Dashboard',
            icon: 'dashboard',
            route: '/dashboard'
        },
        {
            id: 'list',
            title: 'List View',
            icon: 'list ul',
            route: '/list',
            badge: '12'
        },
        {
            id: 'table',
            title: 'Table View',
            icon: 'table',
            route: '/table'
        }
    ];

    private readonly dashboardStats: DashboardStats[] = [
        {
            title: 'Total Users',
            value: 2543,
            icon: 'users',
            color: 'blue',
            trend: { value: 12, isPositive: true }
        },
        {
            title: 'Active Sessions',
            value: 847,
            icon: 'wifi',
            color: 'green',
            trend: { value: 8, isPositive: true }
        },
        {
            title: 'Revenue',
            value: '$12,845',
            icon: 'dollar sign',
            color: 'orange',
            trend: { value: 5, isPositive: false }
        },
        {
            title: 'New Orders',
            value: 156,
            icon: 'shopping cart',
            color: 'red',
            trend: { value: 15, isPositive: true }
        }
    ];

    getMenuItems(): MenuItem[] {
        return this.menuItems;
    }

    getDashboardStats(): DashboardStats[] {
        return this.dashboardStats;
    }

    toggleSidebar(): void {
        this._sidebarCollapsed.update(collapsed => !collapsed);
    }

    setSidebarCollapsed(collapsed: boolean): void {
        this._sidebarCollapsed.set(collapsed);
    }

    setCurrentRoute(route: string): void {
        this._currentRoute.set(route);
    }

    navigateTo(route: string): void {
        this.router.navigate([route]);
        this.setCurrentRoute(route);
    }

    updateUserProfile(profile: Partial<UserProfile>): void {
        this._userProfile.update(current => ({ ...current, ...profile }));
    }

    isActiveRoute(route: string): boolean {
        return this.router.url === route;
    }
}
