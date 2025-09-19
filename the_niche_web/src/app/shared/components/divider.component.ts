import {Component, Input} from "@angular/core";


@Component({
    selector: "app-divider",
    template:`
        <h1>
            <ng-content></ng-content>
        </h1>   
        <div class="ui divider"></div>
    `,
    styles:`
        :host {
            display: block;
            margin-top: 20px;
        }
    `
})
export class DividerComponent{
    @Input() title: string;
    constructor() {
        this.title = ""
    }
}
