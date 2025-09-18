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
import {HouseService} from "../house.service";

@Component({
  selector: 'house-form',
    imports: [
        InputComponent,
        FormsModule,
        ReactiveFormsModule,
        JsonPipe
    ],
  templateUrl: './house-form.component.html',
  styleUrl: './house-form.component.css',
  standalone:true
})
export class HouseFormComponent implements  OnInit{
      unitForm:FormGroup;
      constructor(private fb: FormBuilder,private unitService:HouseService) {
        this.unitForm = fb.group({
               'name':new FormControl('', [Validators.required,Validators.minLength(7),Validators.maxLength(50)]),
               'description':new FormControl('',[Validators.required,Validators.minLength(7)]),
               'address':new FormControl('', [Validators.required,Validators.minLength(50)]),
               'notes':new FormControl('' ),
               'rooms':this.fb.array([this.createRoomForm()])
        });
      }

      createRoomForm():FormGroup{
        return this.fb.group({
              roomNumber:new FormControl('', [Validators.required]),
              capacity:new FormControl('', [Validators.required]),
              notes:new FormControl('', [Validators.required])
          })
      }

      ngOnInit(): void {
      }

        get unitNameControl():FormControl{ return  (this.unitForm.get('name') )as FormControl;}
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
          console.log(this.unitForm.value);
          this.unitService.saveHouse(this.unitForm.value)
              .subscribe((res)=>{
                  console.log(res)
              });
      }

}
