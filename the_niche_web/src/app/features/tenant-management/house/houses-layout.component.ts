import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, NavigationEnd, Router, RouterLink, RouterOutlet} from "@angular/router";
import {filter} from "rxjs";
import {JsonPipe, NgForOf, NgIf} from "@angular/common";


@Component({
    selector: 'houses-list',
    template: `
        <div class="ui breadcrumb" style="margin-bottom: 16px">
            @for (crumb of breadcrumbs; track crumb.url; let last = $last) {
                @if (!last) {
                    <a [routerLink]="crumb.url" class="section">{{ crumb.label }}</a>
                    <div class="divider"> / </div>
                } @else {
                    <div class="active section">{{ crumb.label }}</div>
                }

            } @empty {
                <span class="section">Home</span>
            }
        </div>
        <router-outlet></router-outlet>
    `,
    imports: [
        RouterOutlet,
        RouterLink,
        NgIf,
        NgForOf,
        JsonPipe
    ]
})
export  class HousesLayoutComponent implements  OnInit {

    breadcrumbs: Array<{ label: string, url: string }> = [];

    constructor(private router:Router, private activatedRoute: ActivatedRoute) {

    }
    ngOnInit(): void {
        this.router.events.pipe(
            filter(event => event instanceof NavigationEnd)
        ).subscribe(() => {
            this.breadcrumbs = this.buildBreadcrumbs(this.activatedRoute.root);
        });
    }
    // Inside your HousesComponent or a dedicated BreadcrumbComponent
    buildBreadcrumbs(route: ActivatedRoute, url: string = '', breadcrumbs: any[] = []): any[] {
        const children: ActivatedRoute[] = route.children;

        if (children.length === 0) return breadcrumbs;

        for (const child of children) {
            const routeURL: string = child.snapshot.url.map(segment => segment.path).join('/');
            if (routeURL !== '') {
                url += `/${routeURL}`;
            }

            // Check if a breadcrumb label is defined in route data
            let label = child.snapshot.data['breadcrumb'];

            // OPTIONAL: If the path is an ID (like :houseId),
            // you could override the label with the actual ID or a name
            if (!label && child.snapshot.params['houseId']) {
                label = `House ${child.snapshot.params['houseId']}`;
            }

            if (label) {
                breadcrumbs.push({ label, url });
            }

            return this.buildBreadcrumbs(child, url, breadcrumbs);
        }
        return breadcrumbs;
    }
}
