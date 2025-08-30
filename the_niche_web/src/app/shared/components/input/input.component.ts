import {Component, Input} from '@angular/core';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {JsonPipe, NgClass} from "@angular/common";


@Component({
  selector: 'app-input',
    imports: [
        ReactiveFormsModule,
        NgClass,
        JsonPipe
    ],
  templateUrl: './input.component.html',
  styleUrl: './input.component.css',
  standalone:true
})
export class InputComponent {

   @Input()
   control!: FormControl;
   @Input()
   label!:string;

   @Input() required = false;

  constructor() {
  }
  get isValid(){
    return  this.control.errors && this.control.touched && this.control.dirty && this.control.invalid;
  }
  get minlengthError() {
    return this.control.errors?.['minlength'];
  }
  get maxLengthError() {
    return this.control.errors?.['maxlength'];
  }
}
