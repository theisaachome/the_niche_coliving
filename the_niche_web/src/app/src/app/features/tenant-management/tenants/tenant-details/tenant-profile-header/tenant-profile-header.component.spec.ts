import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TenantProfileHeaderComponent } from './tenant-profile-header.component';

describe('TenantProfileHeaderComponent', () => {
  let component: TenantProfileHeaderComponent;
  let fixture: ComponentFixture<TenantProfileHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TenantProfileHeaderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TenantProfileHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
