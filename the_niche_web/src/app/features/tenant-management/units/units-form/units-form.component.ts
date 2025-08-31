import {Component, OnInit} from '@angular/core';
import {InputComponent} from '../../../../shared/components/input/input.component';
import {
    AbstractControl,
    Form,
    FormArray,
    FormBuilder,
    FormControl,
    FormGroup,
    FormsModule,
    ReactiveFormsModule,
    Validators
} from '@angular/forms';
import {JsonPipe, NgForOf} from '@angular/common';
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
                Validators.minLength(7),
                // Validators.maxLength(50)
              ]),
               'description':new FormControl('',[Validators.required,Validators.minLength(7)]),
                  'address':new FormControl('', [
                Validators.required,
                Validators.minLength(5)
              ]),
              'notes':new FormControl('' ),
              'rooms':this.fb.array([this.createRoomForm()])
        });
      }

      createRoomForm():FormGroup{
        return this.fb.group({
              room_number:new FormControl('', [Validators.required]),
              capacity:new FormControl('', [Validators.required]),
              notes:new FormControl('', [Validators.required])
          })
      }

      ngOnInit(): void {
      }

        get unitNameControl():FormControl{ return  (this.unitForm.get('unit_name') )as FormControl;}
        get descriptionControl():FormControl{ return  (this.unitForm.get('description') )as FormControl;}
        get addressControl(){ return (this.unitForm.get('address'))as FormControl;}
        get noteControl(){ return (this.unitForm.get('notes'))as FormControl;}
        get roomsArray ():FormArray{return this.unitForm.get('rooms') as FormArray;}
        trackById(index: number, item: AbstractControl) {
            return item.value.id ?? index;  // or whatever unique identifier you have
        }

        onAddRoom(){
          this.roomsArray.push(this.createRoomForm());
        }
        onRemoveRoom(index:number){
          this.roomsArray.removeAt(index);
        }
      onSubmit(){
          const newUnit= {
              id:Date.now(),
              unit_name:this.unitForm.get('unit_name')?.value,
              notes:this.unitForm.get('notes')?.value,
              address:this.unitForm.get('address')?.value,
              description:this.unitForm.get('description')?.value,
          }
          this.unitService.addNewUnit(newUnit)
      }

}
