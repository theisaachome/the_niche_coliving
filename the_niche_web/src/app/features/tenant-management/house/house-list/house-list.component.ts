import {Component, OnInit} from '@angular/core';
import {HouseFormComponent} from '../house-form/house-form.component';
import {map, Observable} from 'rxjs';
import {AsyncPipe, NgIf} from '@angular/common';
import {HouseService} from "../house.service";
import {House, PageResponse, UnitsSummary} from "../house.model";
import {ActivatedRoute, Router, RouterLink, RouterOutlet} from "@angular/router";
import {DividerComponent} from "../../../../shared/components/divider.component";
import {TableComponent} from "../../../../shared/components/table/table.component";

@Component({
  selector: 'app-house-list',
    imports: [HouseFormComponent, AsyncPipe, RouterOutlet, DividerComponent, TableComponent, NgIf, RouterLink],
  templateUrl: './house-list.component.html',
  styleUrl: './house-list.component.css',
  standalone:true
})
export class HouseListComponent implements OnInit{
  house$: Observable<House[]> | undefined;
  meta?: Omit<PageResponse<any>, 'content'>;

    headers=[
        { key:'id',label:'#No'},
        { key:'houseName',label:'houseName'},
        { key:'description',label:'description'}
    ]
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
    }

    onViewDetail(id:string){
        this.router.navigate(['/houses', id]);
    }
}
