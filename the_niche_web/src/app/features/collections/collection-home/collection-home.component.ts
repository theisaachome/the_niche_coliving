import { Component } from '@angular/core';
import {DividerComponent} from "../../../shared/components/divider.component";
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {TabComponent} from "../../../shared/components/tab/tab.component";

@Component({
  selector: 'app-collection-home',
    imports: [
        DividerComponent,
        RouterOutlet,
        RouterLink,
        RouterLinkActive,
        TabComponent
    ],
  templateUrl: './collection-home.component.html',
  styleUrl: './collection-home.component.css'
})
export class CollectionHomeComponent {

    headers=[
        {}
    ]

}
