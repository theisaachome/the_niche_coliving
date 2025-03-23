import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {JsonPipe, NgForOf} from '@angular/common';
declare var $: any;

@Component({
  selector: 'app-transaction-form',
  imports: [
    NgForOf,
    JsonPipe,
    ReactiveFormsModule
  ],
  templateUrl: './transaction-form.component.html',
  styleUrl: './transaction-form.component.css',
  standalone:true
})
export class TransactionFormComponent implements OnInit,AfterViewInit{
  selectedAccount: string = ''; // Stores the selected account ID
  currentDate = new Date();

  @ViewChild('calendar', { static: false }) calendar!: ElementRef;

  accounts=[
      {id:1,name:"Cash "},
      {id:2,name:"May Bank "},
      {id:3,name:"RHB Bank"},
      {id:4,name:"CIMB Bank "},
      {id:5,name:"Alice Bank "},
    ]

  categories = [
    { id: 1, name: 'Food & Groceries' },
    { id: 2, name: 'Transportation' },
    { id: 3, name: 'Rent & Utilities' },
    { id: 4, name: 'Entertainment' },
    { id: 5, name: 'Healthcare' }
  ];

  transactionForm = new FormGroup({
      amount: new FormControl('',
        [Validators.required]),
      date: new FormControl(''),
      account: new FormControl(''),
      category: new FormControl(''),
      note: new FormControl('',
        [
          Validators.required,
          Validators.minLength(15)]),
      description:new FormControl('')
  });
    ngOnInit() {
      console.log(this.currentDate)
    }

  onFormSubmit(){

  }


  ngAfterViewInit() {
    $('.selection.dropdown').dropdown({
      onChange: (value: string) => {
        this.selectedAccount = value; // Update Angular variable
      }
    });
    $(this.calendar.nativeElement).calendar({
      type: 'date'
    });
  }

}
