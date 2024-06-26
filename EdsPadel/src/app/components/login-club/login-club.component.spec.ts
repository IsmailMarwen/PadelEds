import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginClubComponent } from './login-club.component';

describe('LoginClubComponent', () => {
  let component: LoginClubComponent;
  let fixture: ComponentFixture<LoginClubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginClubComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoginClubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
