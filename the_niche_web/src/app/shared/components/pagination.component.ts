import {booleanAttribute, Component, EventEmitter, Input, numberAttribute, Output} from "@angular/core";

@Component({
    selector:'app-pagination',
    template:`
        @if (totalPages > 1){
<!--            <div class="ui menu pagination" >-->
<!--                &lt;!&ndash; Items per page &ndash;&gt;-->
<!--                <div class="item">-->
<!--                    Items per page:-->
<!--                    <select class="ui dropdown" [value]="pageSize" (change)="changePageSize($event)">-->
<!--                        @for (size of pageSizeOptions; track size) {-->
<!--                            <option [value]="size">{{ size }}</option>-->
<!--                        }-->
<!--                    </select>-->
<!--                </div>-->

<!--                &lt;!&ndash; Current range &ndash;&gt;-->
<!--                <div class="item">-->
<!--                    {{ startItem }} – {{ endItem }} of {{ totalElements }}-->
<!--                </div>-->

<!--                &lt;!&ndash; First page &ndash;&gt;-->
<!--                <a class="icon item" [class.disabled]="pageNo === 0" (click)="goFirst()">-->
<!--                    <i class="step backward icon"></i>-->
<!--                </a>-->

<!--                &lt;!&ndash; Previous page &ndash;&gt;-->
<!--                <a class="icon item" [class.disabled]="pageNo === 0" (click)="goPrevious()">-->
<!--                    <i class="left chevron icon"></i>-->
<!--                </a>-->

<!--                &lt;!&ndash; Next page &ndash;&gt;-->
<!--                <a class="icon item" [class.disabled]="pageNo >= totalPages - 1" (click)="goNext()">-->
<!--                    <i class="right chevron icon"></i>-->
<!--                </a>-->

<!--                &lt;!&ndash; Last page &ndash;&gt;-->
<!--                <a class="icon item" [class.disabled]="pageNo >= totalPages - 1" (click)="goLast()">-->
<!--                    <i class="step forward icon"></i>-->
<!--                </a>-->
<!--            </div>-->

            <div class="custom-pagination">

                <!-- Items per page -->
                <div class="pagination-item">
                    Items per page:
                    <select class="pagination-dropdown" [value]="pageSize" (change)="changePageSize($event)">
                        @for (size of pageSizeOptions; track size) {
                             <option [value]="size">{{ size }}</option> 
                        }
                     </select>
                </div>    
                 <!-- Current range -->
                <div class="pagination-item">
                    {{ startItem }} – {{ endItem }} of {{ totalElements }}
                </div>

                <!-- First page -->
                <button class="pagination-button" [disabled]="pageNo === 0" (click)="goFirst()">
                    <i class="step backward icon"></i>
                </button>

                <!-- Previous page -->
                <button class="pagination-button" [disabled]="pageNo === 0" (click)="goPrevious()">
                    <i class="left chevron icon"></i>
                </button>

                <!-- Next page -->
                <button class="pagination-button" [disabled]="pageNo >= totalPages - 1" (click)="goNext()">
                    <i class="right chevron icon"></i>
                </button>

                <!-- Last page -->
                <button class="pagination-button" [class.disabled]="pageNo >= totalPages - 1" (click)="goLast()">
                   <i class="step forward icon"></i>
                </button>

            </div>
            
        }
    `,
    styles:`
        /* Container */
        .custom-pagination {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            padding: 0.5rem;
            border-radius: 6px;
            font-family: Arial, sans-serif;
            flex-wrap: wrap;
        }

        /* Items per page and range */
        .pagination-item {
            font-size: 0.9rem;
            color: #333;
            display: flex;
            align-items: center;
        }
        .pagination-item select {
            padding: 0.5rem;
        }

        /* Dropdown */
        .pagination-dropdown {
            margin-left: 0.25rem;
            padding: 0.2rem 0.4rem;
            border-radius: 4px;
            border: 1px solid #ccc;
            min-width: 50px;
        }

        /* Buttons */
        .pagination-button {
            width: 36px;               /* circle size */
            height: 36px;
            padding: 0;                /* remove padding */
            border: 1px solid #ccc;
            border-radius: 50%;        /* circle shape */
            background-color: white;
            cursor: pointer;
            font-size: 0.85rem;
            display: inline-flex;      /* use flex for centering */
            align-items: center;       /* vertical center */
            justify-content: center;   /* horizontal center */
            text-align: center;        /* center text inside flex */
            line-height: 1;            /* prevent line-height offset */
            transition: background 0.2s, color 0.2s;
        }

        /* Hover */
        .pagination-button:not(:disabled):hover {
            background-color: #e0e0e0;
        }

        /* Active / Disabled */
        .pagination-button:disabled {
            cursor: not-allowed;
            opacity: 0.5;
        }

        /* Responsive */
        @media (max-width: 480px) {
            .custom-pagination {
                flex-direction: column;
                align-items: flex-start;
            }
            .pagination-item {
                margin-bottom: 0.25rem;
            }
        }

    `
})
export class PaginationComponent{

    /** Current page (0-based index) */
    @Input({transform: numberAttribute}) pageNo = 0;

    /** Number of items per page */
    @Input({transform: numberAttribute}) pageSize = 20;

    /** Total number of items */
    @Input({transform: numberAttribute}) totalElements = 0;

    /** Total number of pages (optional, computed if missing) */
    // @Input() totalPages?: number;

    /** Whether this is the last page */
    @Input({transform: booleanAttribute}) last = false;

    /** Dropdown options for items per page */
    @Input() pageSizeOptions = [5, 25, 50, 100];

    /** Event when page changes (emits 0-based page number) */
    @Output() pageChange = new EventEmitter<number>();

    /** Event emitted when page size changes */
    @Output() pageSizeChange = new EventEmitter<number>();


    get totalPages():number {
        return Math.ceil(this.totalElements / this.pageSize);
    }
    /** Called when a page button is clicked */
    goToPage(page: number) {
        if (page < 0 || page >= this.totalPages || page === this.pageNo) return;
        this.pageChange.emit(page);
    }
    /** Start index of current page (1-based) */
    get startItem():number {
        return this.totalElements ===  0 ? 0 : (this.pageNo * this.pageSize) + 1;
    }
    /** End index of current page */
    get endItem(): number {
        return Math.min((this.pageNo + 1) * this.pageSize, this.totalElements);
    }

    /** Navigate to previous page */
    goPrevious() {
        if (this.pageNo > 0) this.pageChange.emit(this.pageNo - 1);
    }

    /** Navigate to next page */
    goNext() {
        if (this.pageNo < this.totalPages - 1) this.pageChange.emit(this.pageNo + 1);
    }

    /** Navigate to first page */
    goFirst() {
        if (this.pageNo > 0) this.pageChange.emit(0);
    }

    /** Navigate to last page */
    goLast() {
        if (this.pageNo < this.totalPages - 1) this.pageChange.emit(this.totalPages - 1);
    }

    /** Handle page size change */
    changePageSize(event: Event) {
        const newSize = +(event.target as HTMLSelectElement).value;
        this.pageSizeChange.emit(newSize);
    }

    /** Helper to generate page numbers for template */
    get pages(): number[] {
        const total = this.totalPages;
        const current = this.pageNo;

        const maxButtons = 5; // max visible buttons
        let start = Math.max(0, current - Math.floor(maxButtons / 2));
        let end = start + maxButtons;

        if (end > total) {
            end = total;
            start = Math.max(0, end - maxButtons);
        }

        return Array.from({ length: end - start }, (_, i) => start + i);
    }

}
