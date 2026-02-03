import {Component, inject, input, output} from '@angular/core';
import {MenuItem} from "../../../core/models/menu-item.model";
import {NavigationService} from "../../../core/services/navigation.service";

@Component({
  selector: 'app-sidebar',
  imports: [],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss',
})
export class SidebarComponent {
  private navigationService = inject(NavigationService);

  // Input/Output using new signal-based approach
  collapsed = input<boolean>(false);
  toggleSidebar = output<void>();

  menuItems: MenuItem[] = this.navigationService.getMenuItems();

  navigateTo(route: string): void {
    this.navigationService.navigateTo(route);
  }

  isActive(route: string): boolean {
    return this.navigationService.isActiveRoute(route);
  }

  onToggleSidebar(): void {
    this.toggleSidebar.emit();
  }

}
