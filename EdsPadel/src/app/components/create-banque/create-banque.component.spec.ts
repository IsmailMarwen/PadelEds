import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBanqueComponent } from './create-banque.component';

describe('CreateBanqueComponent', () => {
  let component: CreateBanqueComponent;
  let fixture: ComponentFixture<CreateBanqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateBanqueComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateBanqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
