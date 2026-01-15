import {AfterViewInit, Component, OnInit} from '@angular/core';
import {map, Observable} from 'rxjs';
import {AsyncPipe, NgClass, NgIf} from '@angular/common';
import {HouseService} from "../house.service";
import {House, HouseOverviewResponse, HouseResponse} from "../house.model";
import { Router, RouterLink} from "@angular/router";
import {DividerComponent} from "../../../../shared/components/divider.component";
import {PageResponse} from "../../../../shared/base.model";
import {BaseModalConfig} from "../../../../shared/components/modal/base-modal-config";
import {HouseEditComponent} from "../house-edit/house-edit.component";
import {ModalComponent} from "../../../../shared/components/modal.component";

declare var $: any;

@Component({
  selector: 'app-house-list',
    imports: [AsyncPipe, DividerComponent, RouterLink, NgClass, HouseEditComponent, ModalComponent],
  templateUrl: './house-list.component.html',
  styleUrl: './house-list.component.css',
  standalone:true
})
export class HouseListComponent implements OnInit,AfterViewInit{

    modalOpen=false;
    showModal = false;
    isEditMode=false;
    selectedHouse?:HouseResponse;
  house$: Observable<HouseOverviewResponse[]> | undefined;
  meta?: Omit<PageResponse<any>, 'content'>;

  constructor(private houseService:HouseService,
              private router: Router) {}

    ngAfterViewInit(): void {
        $('.ui.dropdown').dropdown({
            on: 'hover'
        });
    }
  ngOnInit(): void {
   // this.house$ = this.houseService.getAllPagedList()
   //     .pipe(
   //         map((res:PageResponse<House>)=>{
   //              this.meta={
   //                  pageNo:res.pageNo,
   //                  pageSize:res.pageSize,
   //                  totalElement:res.totalElement,
   //                  totalPage:res.totalPage,
   //                  last:res.last
   //              }
   //              return res.content;
   //         }),
   //     );
      this.house$ = this.houseService.getAllHouseOverview();
  }


    onViewDetail(id:string){
        // this.router.navigate(['/houses', id]);
        this.showModal = !this.showModal;
    }

    onApprove() {
        console.log("Approved ✅");
    }

    onNeutral() {
        console.log("Neutral 🤔");
    }

    openModal(house?: HouseResponse) {
        this.selectedHouse = house;
        this.modalOpen = true;
    }
    onModalClosed() {
        this.modalOpen = false;
        console.log('Modal closed ❌');
    }



}
