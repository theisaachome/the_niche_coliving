import {Component} from "@angular/core";
import {RouterOutlet} from "@angular/router";


@Component({
    selector: 'houses-list',
    template: `
        <div class="ui breadcrumb">
            <a class="section">Home</a>
            <div class="divider"> / </div>
            <a class="section">Store</a>
            <div class="divider"> / </div>
            <div class="active section">T-Shirt</div>
        </div>
        <router-outlet></router-outlet>
    `,
    imports: [
        RouterOutlet
    ]
})
export  class HousesComponent{}
