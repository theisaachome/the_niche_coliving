import {Component, Input} from '@angular/core';
import {DividerComponent} from "../../../shared/components/divider.component";

interface Item{
    id:number;
    image:string;
    title:string;
    description:string;
}


@Component({
  selector: 'app-items',
    imports: [
        DividerComponent
    ],
  templateUrl: './items.component.html',
  styleUrl: './items.component.css'
})
export class ItemsComponent {
    @Input() data:Item[]=[];


}
