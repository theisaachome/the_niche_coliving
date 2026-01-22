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
    ],
  templateUrl: './room-form.component.html',
  styleUrl: './room-form.component.css',
})
export class RoomFormComponent implements OnInit {

    @Input() room!:any;
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
        this.setRoomFormFields();
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

    setRoomFormFields(){
        if(this.room){
            this.roomForm.patchValue({
                'roomNumber': this.room.roomNumber,
                'roomType': this.room.roomType,
                'capacity': this.room.capacity,
                'remark': this.room.remark
            });
        }
    }
    onSubmit(){

        if(this.isEditMode){
            this.isLoading = true;
            this.updateRoom()
        }else {
            this.isLoading = true;
            this.createNewRoom()
        }

    }
    updateRoom(){
        this.roomService.updateRoom(this.houseId,this.roomForm.value).subscribe({
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
    createNewRoom(){

        let data = this.roomForm.value;
        this.roomService.saveRoom(this.houseId, data).subscribe({
            next:(res)=>{
                console.log(res);
                this.formSubmitted.emit();
            },
            error:(err)=>{
                console.log(err);
                this.isLoading = false;
            },
            complete:()=>{this.isLoading = false;}
        })
    }
    onCancel(){
        this.formSubmitted.emit();
    }
}
