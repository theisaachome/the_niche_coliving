import {AfterViewInit, Component, ElementRef, EventEmitter, Input, OnDestroy, Output, ViewChild} from "@angular/core";



declare var $:any;


export interface DropdownItem {
    label: string;
    value: any;
}

@Component({
    selector: 'app-fui-dropdown',
    template: `
        <div class="ui compact menu">
            <div #dropdown class="ui simple dropdown item">
                {{ label }}
                <i class="dropdown icon"></i>

                <div class="menu">
                    @for (item of items; track item.value) {
                        <div
                           class="item"
                           [attr.data-value]="item.value">
                            {{ item.label }}
                        </div>   
                    }
                </div>
            </div>
        </div>
    `
})
export class FuiDropdownComponent implements AfterViewInit,OnDestroy{

   @Input() label = "Select";
   @Input() items: DropdownItem []=[];
    @Output() valueChange = new EventEmitter<any>();

    @ViewChild('dropdown') dropdown!: ElementRef;

    ngAfterViewInit(): void {
        $(this.dropdown.nativeElement).dropdown({
            onChange: (value: any) => {
                this.valueChange.emit(value);
            }
        });
    }

    ngOnDestroy(): void {
        if (this.dropdown) {
            $(this.dropdown.nativeElement).dropdown('destroy');
        }
    }

}
