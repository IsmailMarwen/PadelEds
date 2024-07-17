import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCategorieAbonnementtComponent } from './update-categorie-abonnementt.component';

describe('UpdateCategorieAbonnementtComponent', () => {
  let component: UpdateCategorieAbonnementtComponent;
  let fixture: ComponentFixture<UpdateCategorieAbonnementtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateCategorieAbonnementtComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateCategorieAbonnementtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
