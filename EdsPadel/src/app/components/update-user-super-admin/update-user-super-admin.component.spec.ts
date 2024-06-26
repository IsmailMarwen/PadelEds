import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserSuperAdminComponent } from './update-user-super-admin.component';

describe('UpdateUserSuperAdminComponent', () => {
  let component: UpdateUserSuperAdminComponent;
  let fixture: ComponentFixture<UpdateUserSuperAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateUserSuperAdminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateUserSuperAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
