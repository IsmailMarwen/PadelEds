import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTypAbonnementComponent } from './create-typ-abonnement.component';

describe('CreateTypAbonnementComponent', () => {
  let component: CreateTypAbonnementComponent;
  let fixture: ComponentFixture<CreateTypAbonnementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateTypAbonnementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateTypAbonnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
