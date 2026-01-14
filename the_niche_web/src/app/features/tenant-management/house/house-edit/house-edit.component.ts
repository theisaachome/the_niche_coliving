import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {InputComponent} from '../../../../shared/components/input/input.component';
import {
    AbstractControl,
    FormBuilder,
    FormControl,
    FormGroup,
    FormsModule,
    ReactiveFormsModule,
    Validators
} from '@angular/forms';
import {HouseService} from "../house.service";
import {House, HouseResponse} from "../house.model";
import {JsonPipe} from "@angular/common";

@Component({
  selector: 'house-edit',
    imports: [
        InputComponent,
        FormsModule,
        ReactiveFormsModule,
        JsonPipe,
    ],
  templateUrl: './house-edit.component.html',
  styleUrl: './house-edit.component.css',
  standalone:true
})
export class HouseEditComponent implements  OnInit {
       @Output() submitted = new EventEmitter<void>();

       @Input() house?:any;
       @Input({required:true}) isEdit = false;
       houseForm:FormGroup;
      constructor(private fb: FormBuilder,private houseService:HouseService) {
        this.houseForm = fb.group({
               'name':new FormControl('', [Validators.required,Validators.minLength(7),Validators.maxLength(50)]),
               'location':new FormControl('', [Validators.required,Validators.minLength(7),Validators.maxLength(50)]),
               'remark':new FormControl('' )
        });
      }

      ngOnInit(): void {
          console.log(this.house)
          if (this.house) {
              this.houseForm.patchValue({
                  name: this.house.name,
                  location: this.house.location,
                  remark: this.house.remark
              })
          } else {
              // reset for New Entry
              this.houseForm.reset();
          }
      }

      get nameController():FormControl{ return  (this.houseForm.get('name') )as FormControl;}
      get locationController() : FormControl {return  (this.houseForm.get('location')) as  FormControl;}
      get remarkController(){ return (this.houseForm.get('remark'))as FormControl;}

      trackById(index: number, item: AbstractControl) {
            return item.value.id ?? index;  // or whatever unique identifier you have
      }
      onSubmit(){
          console.log(this.houseForm.value);
          if(!this.isEdit){
              this.houseService.saveHouse(this.houseForm.value)
                  .subscribe({
                      next:(res)=>{
                          console.log(res)
                          this.submitted.emit(); // notify parent
                      },
                      error: (err) => {
                          console.error(err);
                      }
                  });
          }else {
              this.houseService.updateHouse(this.house.id,this.houseForm.value)
                  .subscribe({
                      next:(res)=>{
                          console.log(res)
                          this.house = res;
                          this.submitted.emit(); // notify parent
                      },
                      error: (err) => {
                          console.error(err);
                      }
                  });
          }

      }


}
