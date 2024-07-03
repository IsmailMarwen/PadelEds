import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTauxTvaComponent } from './update-taux-tva.component';

describe('UpdateTauxTvaComponent', () => {
  let component: UpdateTauxTvaComponent;
  let fixture: ComponentFixture<UpdateTauxTvaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateTauxTvaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateTauxTvaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
