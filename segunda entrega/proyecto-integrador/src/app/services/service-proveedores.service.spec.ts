import { TestBed } from '@angular/core/testing';

import { ServiceProveedoresService } from './service-proveedores.service';

describe('ServiceProveedoresService', () => {
  let service: ServiceProveedoresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceProveedoresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
