import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigAppWebComponent } from './config-app-web.component';

describe('ConfigAppWebComponent', () => {
  let component: ConfigAppWebComponent;
  let fixture: ComponentFixture<ConfigAppWebComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfigAppWebComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfigAppWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
