import {Component, inject} from '@angular/core';
import {NavigationService} from "../../../core/services/navigation.service";
import {HeaderComponent} from "../header/header.component";
import {RouterOutlet} from "@angular/router";
import {CommonModule} from "@angular/common";
import {SidebarComponent} from "../sidebar/sidebar.component";

@Component({
  selector: 'app-layout',
  imports: [CommonModule, RouterOutlet, SidebarComponent, HeaderComponent],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss',
})
export class LayoutComponent {

  private navigationService = inject(NavigationService);

  sidebarCollapsed = this.navigationService.sidebarCollapsed;

  ngOnInit(): void {
    // Initialize any layout-specific logic
  }

  onSidebarToggle(): void {
    this.navigationService.toggleSidebar();
  }

}
