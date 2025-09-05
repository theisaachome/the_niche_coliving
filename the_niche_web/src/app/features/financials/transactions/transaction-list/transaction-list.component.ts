import { Component } from '@angular/core';
import {DividerComponent} from "../../../../shared/components/divider.component";
import {SegmentComponent} from "../../../../shared/components/segment.component";

@Component({
  selector: 'app-transaction-list',
    imports: [
        DividerComponent,
        SegmentComponent
    ],
  templateUrl: './transaction-list.component.html',
  styleUrl: './transaction-list.component.css'
})
export class TransactionListComponent {

}
