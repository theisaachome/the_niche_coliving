import { Component } from '@angular/core';
import {AssignmentFormComponent} from "../assignment-form/assignment-form.component";

@Component({
  selector: 'app-assignment-list',
    imports: [
        AssignmentFormComponent
    ],
  templateUrl: './assignment-list.component.html',
  styleUrl: './assignment-list.component.css',
})
export class AssignmentListComponent {



}
