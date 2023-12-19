import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedoresVerComponent } from './proveedores-ver.component';

describe('ProveedoresVerComponent', () => {
  let component: ProveedoresVerComponent;
  let fixture: ComponentFixture<ProveedoresVerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProveedoresVerComponent]
    });
    fixture = TestBed.createComponent(ProveedoresVerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
