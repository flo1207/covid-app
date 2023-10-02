import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinationCenterComponent } from './vaccination-center.component';

describe('VaccinationCenterComponent', () => {
  let component: VaccinationCenterComponent;
  let fixture: ComponentFixture<VaccinationCenterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VaccinationCenterComponent]
    });
    fixture = TestBed.createComponent(VaccinationCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
