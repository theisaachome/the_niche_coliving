import {Component, inject, output} from '@angular/core';
import {NavigationService} from "../../../core/services/navigation.service";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-header',
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  private navigationService = inject(NavigationService);

  toggleSidebar = output<void>();
  userProfile = this.navigationService.userProfile;

  onToggleSidebar(): void {
    this.toggleSidebar.emit();
  }

  onProfileClick(): void {
    // Handle profile dropdown toggle
  }

  onLogout(): void {
    // Handle logout logic
    console.log('Logout clicked');
  }

}
