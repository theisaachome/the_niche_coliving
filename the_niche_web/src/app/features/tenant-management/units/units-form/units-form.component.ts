import {Component, OnInit} from '@angular/core';
import {InputComponent} from '../../../../shared/components/input/input.component';
import {Form, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {JsonPipe} from '@angular/common';
import {UnitsService} from "../units.service";

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
  constructor(private fb: FormBuilder,private unitService:UnitsService) {
    this.unitForm = fb.group({
      'unit_name':new FormControl('', [
        Validators.required,
        Validators.minLength(5),
        // Validators.maxLength(50)
      ]),
      'address':new FormControl('', [
        Validators.required,
        Validators.minLength(5)
      ]),
      'notes':new FormControl('' ),
    });
  }

  ngOnInit(): void {
  }

  get unitNameControl():FormControl{
   return  (this.unitForm.get('unit_name') )as FormControl;
  }

  get addressControl(){ return (this.unitForm.get('address'))as FormControl;}
  get noteControl(){ return (this.unitForm.get('notes'))as FormControl;}

  onSubmit(){
      const newUnit= {...this.unitForm.value}
      this.unitService.addNewUnit(newUnit)
  }

}
