import { Component } from '@angular/core';
import {ModalComponent} from "../modal/modal.component";
import {DividerComponent} from "../../../shared/components/divider.component";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-mods.home',
    imports: [
        ModalComponent,
        DividerComponent,
        NgIf
    ],
  templateUrl: './mods-home.component.html',
  styleUrl: './mods-home.component.css'
})
export class ModsHomeComponent {
    modalOpen=false;

    onClick(){
        this.modalOpen= !this.modalOpen;
    }
}
