import {Component, ElementRef, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-modal',
  imports: [],
  template:`
        <div (click)="onCloseClick()" class="ui dimmer visible active">

            <div
                    (click)="$event.stopPropagation()"
                    class="ui modal visible active">

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
                <div class="actions">
                    <ng-content select="[modal-actions]"></ng-content>
                </div>

            </div>
        </div>
  `,
})
export class ModalComponent implements OnInit,OnDestroy {

    @Output() close = new EventEmitter<void>();

    constructor(private el: ElementRef) {}

    ngOnInit(): void {
        document.body.appendChild(this.el.nativeElement);
    }

    ngOnDestroy(): void {
        this.el.nativeElement.remove();
    }

    onCloseClick(): void {
        this.close.emit();
    }
}
