import {Component, ContentChild, EventEmitter, Input, OnInit, Output, TemplateRef} from '@angular/core';
import {NgClass, NgTemplateOutlet} from "@angular/common";

@Component({
  selector: 'app-table',
    imports: [
        NgClass,
        NgTemplateOutlet
    ],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent  implements OnInit {
    ngOnInit(): void {
        throw new Error("Method not implemented.");
    }

    @Input() data: any[] = [];
    @Input() headers: { key: string, label: string }[] = [];
    @Input() classNames = '';

    // New: Reference for custom action buttons
    @ContentChild('actions') actionsTemplate?: TemplateRef<any>;

    // New: Event emitter for viewing details
    @Output() rowClick = new EventEmitter<any>();

    onRowClick(record: any) {
        this.rowClick.emit(record);
    }

}
