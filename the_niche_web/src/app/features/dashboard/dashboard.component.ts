import {Component, inject} from '@angular/core';
import {AssignmentListComponent} from "../tenant-management/room-assignment/assignment-list/assignment-list.component";
import {DashboardStats} from "../../core/models/menu-item.model";
import {NavigationService} from "../../core/services/navigation.service";

@Component({
  selector: 'app-dashboard',
  imports: [
    AssignmentListComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {


  private navigationService = inject(NavigationService);

  stats: DashboardStats[] = [];

  recentActivity = [
    {
      id: 1,
      user: 'John Doe',
      action: 'Logged in',
      timestamp: new Date(Date.now() - 2 * 60 * 1000),
      type: 'login'
    },
    {
      id: 2,
      user: 'Jane Smith',
      action: 'Created new report',
      timestamp: new Date(Date.now() - 15 * 60 * 1000),
      type: 'create'
    },
    {
      id: 3,
      user: 'Mike Johnson',
      action: 'Updated user profile',
      timestamp: new Date(Date.now() - 30 * 60 * 1000),
      type: 'update'
    },
    {
      id: 4,
      user: 'Sarah Wilson',
      action: 'Deleted old records',
      timestamp: new Date(Date.now() - 45 * 60 * 1000),
      type: 'delete'
    }
  ];
  ngOnInit(): void {
    this.stats = this.navigationService.getDashboardStats();
  }

  getRelativeTime(timestamp: Date): string {
    const now = new Date();
    const diffInMinutes = Math.floor((now.getTime() - timestamp.getTime()) / (1000 * 60));

    if (diffInMinutes < 1) return 'Just now';
    if (diffInMinutes < 60) return `${diffInMinutes} min ago`;

    const diffInHours = Math.floor(diffInMinutes / 60);
    if (diffInHours < 24) return `${diffInHours} hour${diffInHours > 1 ? 's' : ''} ago`;

    const diffInDays = Math.floor(diffInHours / 24);
    return `${diffInDays} day${diffInDays > 1 ? 's' : ''} ago`;
  }

}
