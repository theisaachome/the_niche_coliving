import {Component, Input} from '@angular/core';
import {TimesDirective} from "../../directives/times.directive";

@Component({
  selector: 'app-placeholder',
    imports: [
        TimesDirective
    ],
  templateUrl: './placeholder.component.html',
  styleUrl: './placeholder.component.css'
})
export class PlaceholderComponent {
    @Input() header=true;
    @Input() lines = 3;
    constructor() {
    }
}
