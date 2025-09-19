import {Component} from "@angular/core";
import {RouterOutlet} from "@angular/router";
import {DividerComponent} from "../../shared/components/divider.component";
import {StatisticsComponent} from "./statistics/statistics.component";
import {ItemsComponent} from "./items/items.component";


@Component({
    selector: 'view-home',
    imports: [
        RouterOutlet,
        DividerComponent,
        StatisticsComponent,
        ItemsComponent
    ],
    template: `
        <app-divider>Views </app-divider>
        <div class="ui segment">
            <app-statistics [data]="stats"></app-statistics>
            <app-items [data]="items"></app-items>
<!--            <router-outlet></router-outlet>-->
        </div>
    `
})
export class ViewsHomeComponent{
    stats=[
        {id:1,value:22,label:"# of Users"},
        {id:2,value:900,label:"Revenue"},
        {id:3,value:50,label:"Reviews"},
    ]

    items=[
        {
            id:1,
            image:'assets/images/couch.jpeg',
            title:'Couch',
            description:'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet, aperiam aut beatae consectetur dignissimos exercitationem illum iure labore laboriosam, minima nisi non officia omnis, quas repudiandae saepe sapiente vero vitae!'
        },
        {
            id:2,
            image:'assets/images/dresser.jpeg',
            title:'Dresser',
            description:'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet, aperiam aut beatae consectetur dignissimos exercitationem illum iure labore laboriosam, minima nisi non officia omnis, quas repudiandae saepe sapiente vero vitae!'
        },
    ]
}
