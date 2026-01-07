import {Component, Input, OnInit} from '@angular/core';
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
import {House} from "../house.model";

@Component({
  selector: 'house-edit',
    imports: [
        InputComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
  templateUrl: './house-edit.component.html',
  styleUrl: './house-edit.component.css',
  standalone:true
})
export class HouseEditComponent implements  OnInit {

       @Input() house?:any;
      houseForm:FormGroup;
      constructor(private fb: FormBuilder,private unitService:HouseService) {
        this.houseForm = fb.group({
               'name':new FormControl('', [Validators.required,Validators.minLength(7),Validators.maxLength(50)]),
               'remark':new FormControl('' )
        });
      }

      ngOnInit(): void {
          if (this.house) {
              this.houseForm.patchValue({
                  name: this.house.houseName,
                  remark: this.house.remark
              })
          } else {
              // reset for New Entry
              this.houseForm.reset();
          }
      }

      get nameController():FormControl{ return  (this.houseForm.get('name') )as FormControl;}
      get noteControl(){ return (this.houseForm.get('remark'))as FormControl;}
      trackById(index: number, item: AbstractControl) {
            return item.value.id ?? index;  // or whatever unique identifier you have
      }
      onSubmit(){
          console.log(this.houseForm.value);
          this.unitService.saveHouse(this.houseForm.value)
              .subscribe((res)=>{
                  console.log(res)
              });
      }

}
