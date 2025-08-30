import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {JsonPipe} from '@angular/common';
import {InputComponent} from '../../../shared/components/input/input.component';

@Component({
  selector: 'app-account-form',
  imports: [
    JsonPipe,
    ReactiveFormsModule,
    InputComponent
  ],
  templateUrl: './account-form.component.html',
  styleUrl: './account-form.component.css',
  standalone:true
})
export class AccountFormComponent implements OnInit{
  accountForm= new FormGroup({
    name:new FormControl('', [
      Validators.required,
      Validators.minLength(5),
      // Validators.maxLength(30)
    ]),
    description: new FormControl('', [
      Validators.required,
      // Validators.minLength(10),
      // Validators.maxLength(50)
    ]),
  })
  constructor() {

  }
  ngOnInit() {
  }
  get nameControl(): FormControl {
    return this.accountForm.get('name') as FormControl;
  }

  onSubmitForm(){
    console.log("Form was submitted, data {}", this.accountForm.value);
  }

}
