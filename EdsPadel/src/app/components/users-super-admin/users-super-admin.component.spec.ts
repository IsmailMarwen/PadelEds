import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersSuperAdminComponent } from './users-super-admin.component';

describe('UsersSuperAdminComponent', () => {
  let component: UsersSuperAdminComponent;
  let fixture: ComponentFixture<UsersSuperAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UsersSuperAdminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UsersSuperAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
