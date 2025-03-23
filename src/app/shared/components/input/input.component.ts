import {Component, Input} from '@angular/core';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {JsonPipe, NgIf} from '@angular/common';

@Component({
  selector: 'app-input',
  imports: [
    NgIf,
    JsonPipe,
    ReactiveFormsModule
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

  constructor() {
  }
  get isValid(){
    return  this.control.errors && this.control.touched && this.control.dirty;
  }
  get minlengthError() {
    return this.control.errors?.['minlength'];
  }
  get maxLengthError() {
    return this.control.errors?.['maxlength'];
  }
}
