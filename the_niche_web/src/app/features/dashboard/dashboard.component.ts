import { Component } from '@angular/core';
import {AssignmentListComponent} from "../tenant-management/room-assignment/assignment-list/assignment-list.component";

@Component({
  selector: 'app-dashboard',
  imports: [
    AssignmentListComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
