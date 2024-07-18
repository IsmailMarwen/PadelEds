import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePlageHoraireComponent } from './update-plage-horaire.component';

describe('UpdatePlageHoraireComponent', () => {
  let component: UpdatePlageHoraireComponent;
  let fixture: ComponentFixture<UpdatePlageHoraireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdatePlageHoraireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdatePlageHoraireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
