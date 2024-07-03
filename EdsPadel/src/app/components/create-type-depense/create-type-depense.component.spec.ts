import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTypeDepenseComponent } from './create-type-depense.component';

describe('CreateTypeDepenseComponent', () => {
  let component: CreateTypeDepenseComponent;
  let fixture: ComponentFixture<CreateTypeDepenseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateTypeDepenseComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateTypeDepenseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
