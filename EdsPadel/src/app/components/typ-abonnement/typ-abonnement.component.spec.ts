import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypAbonnementComponent } from './typ-abonnement.component';

describe('TypAbonnementComponent', () => {
  let component: TypAbonnementComponent;
  let fixture: ComponentFixture<TypAbonnementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TypAbonnementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TypAbonnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
