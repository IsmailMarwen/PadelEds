import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PliageHoraireComponent } from './pliage-horaire.component';

describe('PliageHoraireComponent', () => {
  let component: PliageHoraireComponent;
  let fixture: ComponentFixture<PliageHoraireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PliageHoraireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PliageHoraireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
