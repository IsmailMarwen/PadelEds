import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlageHoraireComponent } from './plage-horaire.component';

describe('PlageHoraireComponent', () => {
  let component: PlageHoraireComponent;
  let fixture: ComponentFixture<PlageHoraireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PlageHoraireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PlageHoraireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
