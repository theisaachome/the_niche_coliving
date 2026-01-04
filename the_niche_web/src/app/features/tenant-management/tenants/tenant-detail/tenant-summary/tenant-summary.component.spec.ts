import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TenantSummaryComponent } from './tenant-summary.component';

describe('TenantSummaryComponent', () => {
  let component: TenantSummaryComponent;
  let fixture: ComponentFixture<TenantSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TenantSummaryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TenantSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
