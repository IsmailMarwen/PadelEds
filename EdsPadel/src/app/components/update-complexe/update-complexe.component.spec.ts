import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateComplexeComponent } from './update-complexe.component';

describe('UpdateComplexeComponent', () => {
  let component: UpdateComplexeComponent;
  let fixture: ComponentFixture<UpdateComplexeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateComplexeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateComplexeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
