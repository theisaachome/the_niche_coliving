import {Component, Input, OnInit} from '@angular/core';
import {DividerComponent} from "../../../shared/components/divider.component";

interface Stats{
    id:number,
    value:number,
    label:string,
}
@Component({
  selector: 'app-statistics',
    imports: [
        DividerComponent
    ],
  templateUrl: './statistics.component.html',
  styleUrl: './statistics.component.css'
})
export class StatisticsComponent  implements  OnInit {
    @Input() data:Stats[] = [];
    ngOnInit(): void {
    }

}
