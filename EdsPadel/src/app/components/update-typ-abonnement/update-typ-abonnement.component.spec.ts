import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTypAbonnementComponent } from './update-typ-abonnement.component';

describe('UpdateTypAbonnementComponent', () => {
  let component: UpdateTypAbonnementComponent;
  let fixture: ComponentFixture<UpdateTypAbonnementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateTypAbonnementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateTypAbonnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
