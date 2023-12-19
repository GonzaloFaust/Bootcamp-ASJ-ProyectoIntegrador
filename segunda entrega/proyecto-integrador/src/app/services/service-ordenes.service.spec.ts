import { TestBed } from '@angular/core/testing';

import { ServiceOrdenesService } from './service-ordenes.service';

describe('ServiceOrdenesService', () => {
  let service: ServiceOrdenesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceOrdenesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
