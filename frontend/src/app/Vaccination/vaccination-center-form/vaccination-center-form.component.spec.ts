import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinationCenterFormComponent } from './vaccination-center-form.component';

describe('VaccinationCenterFormComponent', () => {
  let component: VaccinationCenterFormComponent;
  let fixture: ComponentFixture<VaccinationCenterFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VaccinationCenterFormComponent]
    });
    fixture = TestBed.createComponent(VaccinationCenterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
