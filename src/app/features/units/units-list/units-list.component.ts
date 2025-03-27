import {Component, OnInit} from '@angular/core';
import {UnitsFormComponent} from '../units-form/units-form.component';
import {UnitService} from '../../../core/services/unit.service';
import {Observable} from 'rxjs';
import {Unit} from '../../../core/models/models';
import {AsyncPipe} from '@angular/common';

@Component({
  selector: 'app-units-list',
  imports: [UnitsFormComponent, AsyncPipe],
  templateUrl: './units-list.component.html',
  styleUrl: './units-list.component.css',
  standalone:true
})
export class UnitsListComponent implements OnInit{
  unit$!:Observable<Unit[]>;
  constructor(private unitService:UnitService) {
  }
  ngOnInit(): void {
   this.unit$= this.unitService.getAll();
  }
}
