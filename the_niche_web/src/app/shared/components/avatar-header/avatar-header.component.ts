import {Component, Input} from "@angular/core";
import {NgOptimizedImage} from "@angular/common";


@Component({
    selector: "app-avatar-header",
    template: `
        <div class="avatar-header">
            <img class="avatar"
                 [src]="imageUrl"
                 alt="avatar"/>
            <div class="text">
                <h4 class="title">{{tittle}}</h4>
                <h5 class="subtitle">#{{subTitle}}</h5>
            </div>
        </div>
    `,
    styles: `
        .avatar-header {
            display: flex;
            align-items: center;
            gap: 1rem;
        }

        .avatar {
            width: 64px;
            height: 64px;
            border-radius: 50%;
            object-fit: cover;
        }

        .text {
            display: flex;
            flex-direction: column;
        }

        .title {
            margin: 0;
            font-weight: 600;
        }

        .subtitle {
            margin: 0;
            color: #6b7280;
        / / muted gray font-size: 0.9 rem;
        }
    `
})
export class AvatarHeaderComponent{
    @Input({required:true}) tittle = '';

    @Input({required:true}) subTitle = '';

    @Input({required:true}) imageUrl="assets/images/tenant-avatar.png";
}
