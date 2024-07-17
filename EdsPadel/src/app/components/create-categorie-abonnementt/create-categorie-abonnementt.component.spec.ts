import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCategorieAbonnementtComponent } from './create-categorie-abonnementt.component';

describe('CreateCategorieAbonnementtComponent', () => {
  let component: CreateCategorieAbonnementtComponent;
  let fixture: ComponentFixture<CreateCategorieAbonnementtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateCategorieAbonnementtComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateCategorieAbonnementtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
