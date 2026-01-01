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
    template:`
        <label class="label " [ngClass]="{required:required}">{{label}}</label>
        <input type="text"  [formControl]="control">
        <div class="ui warning message">
            @if(isValid){
                <div class="header">Could you check {{label}} input field!</div>
            }
            @if (maxLengthError && isValid){
                <p>Value you entered is {{maxLengthError.actualLength}} characters long it
                    can not be longer than {{maxLengthError.requiredLength}} characters.
                </p>
            }
            @if (minlengthError){
                <p>Value you entered is {{minlengthError.actualLength}} characters long it must be
                    at least {{minlengthError.requiredLength}} characters.
                </p>
            }
            @if(control.errors?.['pattern']){
                <p>Invalid Format</p>
            }
        </div>
    `,
    styles:`
        .label{
            display: block;
            margin: 0 0 .28571429rem 0;
            font-size: .92857143em;
            font-weight: 700;
            text-transform: none;
        }

        input.ng-invalid.ng-touched {
            border: 1px solid #e58181;
        }
    `,
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
