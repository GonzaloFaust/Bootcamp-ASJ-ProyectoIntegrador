import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedoresCrearComponent } from './proveedores-crear.component';

describe('ProveedoresCrearComponent', () => {
  let component: ProveedoresCrearComponent;
  let fixture: ComponentFixture<ProveedoresCrearComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProveedoresCrearComponent]
    });
    fixture = TestBed.createComponent(ProveedoresCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
