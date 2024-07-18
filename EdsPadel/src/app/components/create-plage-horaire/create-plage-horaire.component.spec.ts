import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePlageHoraireComponent } from './create-plage-horaire.component';

describe('CreatePlageHoraireComponent', () => {
  let component: CreatePlageHoraireComponent;
  let fixture: ComponentFixture<CreatePlageHoraireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreatePlageHoraireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreatePlageHoraireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
