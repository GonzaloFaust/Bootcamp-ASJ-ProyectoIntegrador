import { TestBed } from '@angular/core/testing';

import { ServiceProductosService } from './service-productos.service';

describe('ServiceProductosService', () => {
  let service: ServiceProductosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceProductosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
