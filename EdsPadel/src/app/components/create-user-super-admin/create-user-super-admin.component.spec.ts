import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateUserSuperAdminComponent } from './create-user-super-admin.component';

describe('CreateUserSuperAdminComponent', () => {
  let component: CreateUserSuperAdminComponent;
  let fixture: ComponentFixture<CreateUserSuperAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateUserSuperAdminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateUserSuperAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
