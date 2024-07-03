import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTypeDepenseComponent } from './update-type-depense.component';

describe('UpdateTypeDepenseComponent', () => {
  let component: UpdateTypeDepenseComponent;
  let fixture: ComponentFixture<UpdateTypeDepenseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateTypeDepenseComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateTypeDepenseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
