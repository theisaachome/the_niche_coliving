import {Component, ContentChild, ElementRef, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-modal',
  imports: [],
  template:`
        <div (click)="onBackdropClick()" class="ui dimmer visible active" >
            <div (click)="$event.stopPropagation()"
                    class="ui modal visible active" style="padding-bottom: 16px">
                <i (click)="onCloseClick()" class="close icon"></i>

                <!-- TITLE -->
                <div class="header">
                    <ng-content select="[modal-title]"></ng-content>
                </div>

                <!-- CONTENT -->
                <div class="content">
                    <ng-content select="[modal-content]"></ng-content>
                </div>
                
                <!-- ACTIONS -->
                @if (hasActions) {
                    <div class="actions">
                        <ng-content select="[modal-actions]"></ng-content>
                    </div>
                }

            </div>
        </div>
  `,
})
export class ModalComponent implements OnInit,OnDestroy {

    @Output() close = new EventEmitter<void>();
    @Input() closeOnBackdrop = true;
    @ContentChild('modalActions') modalActions!:ElementRef;

    constructor(private el: ElementRef) {}

    ngOnInit(): void {
        document.body.appendChild(this.el.nativeElement);
    }

    ngOnDestroy(): void {
        this.el.nativeElement.remove();
    }
    get hasActions() {
        return !!this.modalActions;
    }
    onBackdropClick(): void {
        if (this.closeOnBackdrop) {
            this.close.emit();
            console.log("close from backdrop");
        }
    }
    onCloseClick(): void {
        this.close.emit();
        console.log("close")
    }
}
