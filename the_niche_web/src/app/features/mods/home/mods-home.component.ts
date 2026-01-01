import { Component } from '@angular/core';
import {DividerComponent} from "../../../shared/components/divider.component";
import {NgIf} from "@angular/common";
import {ModalComponent} from "../../../shared/modal.component";

@Component({
  selector: 'app-mods.home',
    imports: [
        ModalComponent,
        DividerComponent,
        NgIf,
        ModalComponent
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
