import {Component} from "@angular/core";


@Component({
    selector: "app-segment",
    template:`
        <div class="ui placeholder segment">
            <div class="ui icon header">
                <ng-content ></ng-content>
            </div>
            <ng-content select="[segment-body]"></ng-content>
        </div>
    `,
    styles:[
        `
            div.ui.icon.header:empty{
                display: none;
            }
        `
    ]
})
export class SegmentComponent {}
