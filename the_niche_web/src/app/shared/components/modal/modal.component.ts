import {Component, ElementRef, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BaseModalConfig, ModalActionConfig} from "./base-modal-config";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-base-modal',
    imports: [
        NgClass
    ],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent implements OnInit {

    @Input() config:BaseModalConfig = new BaseModalConfig();

    @Output() close = new EventEmitter<boolean>();

    constructor(private el:ElementRef) {
    }

    ngOnInit(): void {
     document.body.appendChild(this.el.nativeElement);
    }

    ngOnDestroy() {
        this.el.nativeElement.remove();
    }

    onCloseClick() {
        this.close.emit(true);
    }

    onActionCall(action:ModalActionConfig){
        if(action.callback){
            action.callback();
        }
        this.onCloseClick();
        this.ngOnInit();
    }
}
