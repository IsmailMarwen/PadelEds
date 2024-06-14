import { TestBed } from '@angular/core/testing';

import { AppwebserviceService } from './appwebservice.service';

describe('AppwebserviceService', () => {
  let service: AppwebserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppwebserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
