import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeAbonnementClubComponent } from './type-abonnement-club.component';

describe('TypeAbonnementClubComponent', () => {
  let component: TypeAbonnementClubComponent;
  let fixture: ComponentFixture<TypeAbonnementClubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TypeAbonnementClubComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TypeAbonnementClubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
