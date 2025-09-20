import {Component, ElementRef, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-modal',
  imports: [],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent implements OnInit {

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
}
