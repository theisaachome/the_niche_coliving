import {
    AfterViewInit,
    Component,
    ElementRef,
    forwardRef,
    Input,
    OnDestroy,
    ViewChild
} from '@angular/core';
import {
    ControlValueAccessor,
    NG_VALUE_ACCESSOR
} from '@angular/forms';

declare var $: any;

export interface SelectOption {
    label: string;
    value: string;
}

@Component({
    selector: 'app-fui-select',
    template: `
            <label>{{ label }}</label>
            <select
                    #select
                    class="ui fluid dropdown"
                    [disabled]="disabled">
                @for (opt of options; track opt.value) {
                    <option [value]="opt.value">
                        {{ opt.label }}
                    </option>   
                }
            </select>
    `,
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => FuiSelectComponent),
            multi: true
        }
    ]
})
export class FuiSelectComponent
    implements ControlValueAccessor, AfterViewInit, OnDestroy {

    @Input() label = '';
    @Input() placeholder = 'Select';
    @Input() options: SelectOption[] = [];

    @ViewChild('select', { static: false })
    select!: ElementRef;

    value: string = '';
    disabled = false;

    private onChange = (value: any) => {};
    private onTouched = () => {};

    ngAfterViewInit(): void {
        setTimeout(() => {
            $(this.select.nativeElement)
                .dropdown({
                    onChange: (value: string) => {
                        this.value = value;
                        this.onChange(value);
                    }
                });
        });
    }

    writeValue(value: any): void {
        this.value = value ?? '';
        if (this.select) {
            $(this.select.nativeElement).dropdown('set selected', this.value);
        }
    }

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }

    setDisabledState(isDisabled: boolean): void {
        this.disabled = isDisabled;
        if (this.select) {
            $(this.select.nativeElement)
                .dropdown(isDisabled ? 'disable' : 'enable');
        }
    }

    ngOnDestroy(): void {
        if (this.select) {
            $(this.select.nativeElement).dropdown('destroy');
        }
    }
}
