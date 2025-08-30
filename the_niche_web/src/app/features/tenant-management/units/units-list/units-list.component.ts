import {Component, OnInit} from '@angular/core';
import {UnitsFormComponent} from '../units-form/units-form.component';
import {Observable} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {UnitsService} from "../units.service";
import {Unit} from "../unit.model";

@Component({
  selector: 'app-units-list',
  imports: [UnitsFormComponent, AsyncPipe],
  templateUrl: './units-list.component.html',
  styleUrl: './units-list.component.css',
  standalone:true
})
export class UnitsListComponent implements OnInit{
  unit$!:Observable<Unit[]>;
  constructor(private unitService:UnitsService) {
  }
  ngOnInit(): void {
   this.unit$= this.unitService.getAllUnits();
  }
}
