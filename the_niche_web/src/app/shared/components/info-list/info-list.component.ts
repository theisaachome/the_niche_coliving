import {Component, Input} from "@angular/core";
import {InfoItem} from "./info-item.model";


@Component({
    selector:'app-info-list',
    template:`
        <div class="info-list">
            @if (title) {
                <h4 class="title">{{ title }}</h4>   
            }
            <div class="items">
                @for (item of infoItems; track item.id) {
                    <div class="item">
                        @if (item.icon) {
                            <i [class]="item.icon"></i>
                        }
                        @if (item.label) {
                            <small class="label">{{ item.label }} : </small>
                        }
                        <p class="value">{{ item.value }}</p>
                    </div>
                }

            </div>
        </div>
    `,
    styles:`
        .info-list {
            display: flex;
            flex-direction: column;
            gap: 0.75rem;
            padding-bottom: 0.75rem;
            border-bottom: 1px solid #e5e7eb; /* light divider */
            margin-bottom: 16px;
        }
        .items {
            display: flex;
            flex-direction: column;
            gap: 0.95rem;
        }
    
        .item {
            display: flex;
            align-items: center;   /* ✅ center vertically */
            gap: 0.5rem;
        }
    
        .item i {
            font-size: 1rem;
            min-width: 1rem;       /* keeps icons aligned */
        }
    
        .label {
            font-size: 0.75rem;
            color: #6b7280;
            white-space: nowrap;
        }
    
        .value {
            font-size: 0.95rem;
        }  
        /*.info-list:last-child {*/
        /*   border-bottom: none;*/
        /*}*/
    `
})
export class InfoListComponent{
    @Input()title?: string;
    @Input() infoItems: InfoItem[]=[];
    @Input() showDivider = false;
}
