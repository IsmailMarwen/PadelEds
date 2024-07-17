import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategorieAbonnementtComponent } from './categorie-abonnementt.component';

describe('CategorieAbonnementtComponent', () => {
  let component: CategorieAbonnementtComponent;
  let fixture: ComponentFixture<CategorieAbonnementtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CategorieAbonnementtComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CategorieAbonnementtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
