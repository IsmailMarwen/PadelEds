import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateActiviteComponent } from './create-activite.component';

describe('CreateActiviteComponent', () => {
  let component: CreateActiviteComponent;
  let fixture: ComponentFixture<CreateActiviteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateActiviteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateActiviteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
