import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionTableComponent } from './gestion-table.component';

describe('GestionTableComponent', () => {
  let component: GestionTableComponent;
  let fixture: ComponentFixture<GestionTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionTableComponent]
    });
    fixture = TestBed.createComponent(GestionTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
