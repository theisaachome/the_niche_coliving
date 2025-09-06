import { Component } from '@angular/core';
import {DividerComponent} from "../../../../shared/components/divider.component";
import {SegmentComponent} from "../../../../shared/components/segment.component";
import {TableComponent} from "../../../../shared/components/table/table.component";
import {Transaction} from "../../finance.model";
import {TransactionService} from "../../services/transaction.services";

@Component({
  selector: 'app-transaction-list',
    imports: [
        DividerComponent,
        SegmentComponent,
        TableComponent
    ],
  templateUrl: './transaction-list.component.html',
  styleUrl: './transaction-list.component.css'
})
export class TransactionListComponent {
    transactionsData :any[] = [];
    headers=[
        { key:'id',label:'#No'},
        { key:'type',label:'Type'},
        { key:'date',label:'Date'},
        { key:'amount',label:'Amount'},
        { key:'description',label:'Description'},
        { key:'paymentMethod',label:'Payment Method'},
        { key:'category',label:'Category'},
        { key:'budget',label:'Budget'},
    ]

    constructor(private transactionService: TransactionService) {
         this.transactionService.getAllTransactions()
            .subscribe(data=>this.transactionsData=data);
    }

}
