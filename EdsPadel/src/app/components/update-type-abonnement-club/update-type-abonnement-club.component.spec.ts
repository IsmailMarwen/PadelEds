import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTypeAbonnementClubComponent } from './update-type-abonnement-club.component';

describe('UpdateTypeAbonnementClubComponent', () => {
  let component: UpdateTypeAbonnementClubComponent;
  let fixture: ComponentFixture<UpdateTypeAbonnementClubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateTypeAbonnementClubComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateTypeAbonnementClubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
