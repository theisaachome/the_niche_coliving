import {Component, OnInit} from '@angular/core';
import {HouseFormComponent} from '../house-form/house-form.component';
import {Observable} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {HouseService} from "../house.service";
import {House, UnitsSummary} from "../house.model";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-units-list',
    imports: [HouseFormComponent, AsyncPipe, RouterOutlet],
  templateUrl: './house-list.component.html',
  styleUrl: './house-list.component.css',
  standalone:true
})
export class HouseListComponent implements OnInit{
  unit$!:Observable<House[]>;
  unitsSummary$!:Observable<UnitsSummary[]>;
  constructor(private unitService:HouseService) {
  }
  ngOnInit(): void {
   this.unit$= this.unitService.getAllUnits();
   this.unitsSummary$ = this.unitService.getUnitSummary();
  }

  onAddHouse(){

  }
}
