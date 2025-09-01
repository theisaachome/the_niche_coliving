import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-not-found',
  imports: [],
    template:`
        <div class="ui middle aligned center aligned grid" style="height: 100vh;">
            <div class="column" style="max-width: 500px;">
                <h1 class="ui icon header">
                    <i class="exclamation triangle red icon"></i>
                    <div class="content">
                        404 - Page Not Found
                        <div class="sub header">
                            Sorry, the page you are looking for doesn’t exist.
                        </div>
                    </div>
                </h1>
                <div class="ui divider"></div>
                <a class="ui primary button" (click)="goHome()">
                    <i class="home icon"></i>
                    Go Back Home
                </a>
            </div>
        </div>
    `,
    styles:`
        .column {
            text-align: center;
        }
    `
})
export class NotFoundComponent {

    constructor(private router: Router) {}

    goHome() {
        this.router.navigate(['/dashboard']);
    }

}
