import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTypeAbonnementClubComponent } from './create-type-abonnement-club.component';

describe('CreateTypeAbonnementClubComponent', () => {
  let component: CreateTypeAbonnementClubComponent;
  let fixture: ComponentFixture<CreateTypeAbonnementClubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateTypeAbonnementClubComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateTypeAbonnementClubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
