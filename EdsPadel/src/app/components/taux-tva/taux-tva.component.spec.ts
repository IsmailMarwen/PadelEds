import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TauxTvaComponent } from './taux-tva.component';

describe('TauxTvaComponent', () => {
  let component: TauxTvaComponent;
  let fixture: ComponentFixture<TauxTvaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TauxTvaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TauxTvaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
