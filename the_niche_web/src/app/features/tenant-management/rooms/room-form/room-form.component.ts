import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputComponent} from "../../../../shared/components/input/input.component";
import {JsonPipe} from "@angular/common";
import {RoomService} from "../room.service";

@Component({
  selector: 'app-room-form',
    imports: [
        ReactiveFormsModule,
        InputComponent,
        JsonPipe
    ],
  templateUrl: './room-form.component.html',
  styleUrl: './room-form.component.css',
})
export class RoomFormComponent implements OnInit {

    @Input({required:true}) houseId!: string;
    @Input({required:true})isEditMode = false;
    @Output() formSubmitted = new EventEmitter<void>();
    isLoading:boolean = false;

    roomForm!: FormGroup;
    constructor(private fb:FormBuilder,
                private roomService: RoomService ) {

    }

    ngOnInit(): void {
        this.constructForm();
    }

    private constructForm(){
        this.roomForm = this.fb.group({
            roomNumber: ['', [Validators.required]],
            roomType: ['', [Validators.required]],
            capacity: ['', [Validators.required]],
            remark: ['']
        });
    }
    get roomNumberController() { return this.roomForm.get('roomNumber') as FormControl; }
    get roomTypeController() { return this.roomForm.get('roomType') as FormControl; }
    get capacityController() { return this.roomForm.get('capacity') as FormControl; }
    get remarkController() { return this.roomForm.get('remark') as FormControl; }
    onSubmit(){
        this.isLoading = true;
        let data = this.roomForm.value;
        this.roomService.saveRoom(this.houseId, data).subscribe({
            next:(res)=>{
                console.log(res);
                this.formSubmitted.emit();
            },
            error:(err)=>{
                console.log(err);
            },
            complete:()=>{this.isLoading = false;}
        })
    }
    onCancel(){
        this.formSubmitted.emit();
    }
}
