import {Component, OnInit} from '@angular/core';
import {InputComponent} from '../../../shared/components/input/input.component';
import {Form, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {JsonPipe} from '@angular/common';

@Component({
  selector: 'app-units-form',
  imports: [
    InputComponent,
    FormsModule,
    ReactiveFormsModule,
    JsonPipe
  ],
  templateUrl: './units-form.component.html',
  styleUrl: './units-form.component.css',
  standalone:true
})
export class UnitsFormComponent  implements  OnInit{
  unitForm:FormGroup;
  constructor(private fb: FormBuilder) {
    this.unitForm = fb.group({
      'name':new FormControl('', [
        Validators.required,
        Validators.minLength(5),
        // Validators.maxLength(50)
      ]),
      'address1':new FormControl('', [
        Validators.required,
        Validators.minLength(5)
      ]),
      'address2':new FormControl('' ),
    });
  }
  ngOnInit(): void {
  }

  get nameControl():FormControl{
   return  (this.unitForm.get('name') )as FormControl;
  }

  get address1Control(){ return (this.unitForm.get('address1'))as FormControl;}
  get address2Control(){ return (this.unitForm.get('address2'))as FormControl;}

  onSubmit(){
    console.log(this.unitForm.controls['name'].value);
  }

}
