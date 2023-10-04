import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { GestionPageGuard } from './gestion-page.guard';

describe('ChatPageGuard', () => {
  let guard: GestionPageGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
    });
    guard = TestBed.inject(GestionPageGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
