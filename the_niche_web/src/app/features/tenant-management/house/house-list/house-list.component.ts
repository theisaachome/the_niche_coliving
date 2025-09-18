import {Component, OnInit} from '@angular/core';
import {map, Observable} from 'rxjs';
import {AsyncPipe, NgIf} from '@angular/common';
import {HouseService} from "../house.service";
import {House} from "../house.model";
import { Router, RouterLink} from "@angular/router";
import {DividerComponent} from "../../../../shared/components/divider.component";
import {PageResponse} from "../../../../shared/base.model";

@Component({
  selector: 'app-house-list',
    imports: [ AsyncPipe, DividerComponent,  RouterLink],
  templateUrl: './house-list.component.html',
  styleUrl: './house-list.component.css',
  standalone:true
})
export class HouseListComponent implements OnInit{
  house$: Observable<House[]> | undefined;
  meta?: Omit<PageResponse<any>, 'content'>;

  constructor(private houseService:HouseService,
              private router: Router,
              ) {
  }
  ngOnInit(): void {
   this.house$ = this.houseService.getAllPagedList()
       .pipe(
           map((res:PageResponse<House>)=>{
                this.meta={
                    pageNo:res.pageNo,
                    pageSize:res.pageSize,
                    totalElement:res.totalElement,
                    totalPages:res.totalPages,
                    last:res.last
                }
                return res.content;
           }),
       );
  }
    addNewHouse() {
      this.router.navigate(['/houses/new']);
    }

    onViewDetail(id:string){
        this.router.navigate(['/houses', id]);
    }
}
