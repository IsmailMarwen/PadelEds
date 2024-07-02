import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTauxTvaComponent } from './create-taux-tva.component';

describe('CreateTauxTvaComponent', () => {
  let component: CreateTauxTvaComponent;
  let fixture: ComponentFixture<CreateTauxTvaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateTauxTvaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateTauxTvaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
