import {Component, Input, OnInit} from '@angular/core';
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-table',
    imports: [
        NgClass
    ],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent  implements OnInit {

    @Input() data:any[] = [];
    @Input() headers:any[] = [];
    @Input('class') classNames = '';

    constructor() {}
    ngOnInit() {}

}
