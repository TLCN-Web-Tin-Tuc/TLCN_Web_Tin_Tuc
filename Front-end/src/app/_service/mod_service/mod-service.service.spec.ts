import { TestBed } from '@angular/core/testing';

import { ModServiceService } from './mod-service.service';

describe('ModServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ModServiceService = TestBed.get(ModServiceService);
    expect(service).toBeTruthy();
  });
});
