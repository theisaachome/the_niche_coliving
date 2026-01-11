import {Component, OnInit} from '@angular/core';
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

@Component({
  selector: 'app-house-list',
    imports: [AsyncPipe, DividerComponent, RouterLink, NgClass, HouseEditComponent, ModalComponent],
  templateUrl: './house-list.component.html',
  styleUrl: './house-list.component.css',
  standalone:true
})
export class HouseListComponent implements OnInit{

    modalOpen=false;

    showModal = false;
    isEditMode=false;
    selectedHouse?:HouseResponse;

    modalConfig: BaseModalConfig = {
        title: 'Default Action',
        actions: [
            // { label: 'Approve', type: 'approve', callback: () => this.onApprove() },
            // { label: 'Neutral', type: 'neutral', callback: () => this.onNeutral() },
            { label: 'Submit', type: 'submit', callback: () => this.onNeutral() },
            { label: 'Cancel', type: 'cancel' } // no callback, just closes
        ]
    };
  house$: Observable<HouseOverviewResponse[]> | undefined;
  meta?: Omit<PageResponse<any>, 'content'>;

  constructor(private houseService:HouseService,
              private router: Router,
              ) {
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


    onClick(){
        this.modalOpen= !this.modalOpen;
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


    openNewHouseModal() {
      this.modalConfig.title="Add New House"
        this.selectedHouse = undefined; // no data → new entry
        this.showModal = true;
    }

    openEditHouseModal(house: HouseResponse) {
      this.modalConfig.title="Edit House"
        this.selectedHouse = house; // pass object for editing
        this.showModal = true;
    }

}
