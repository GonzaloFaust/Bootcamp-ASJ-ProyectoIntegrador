import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductosVerComponent } from './productos-ver.component';

describe('ProductosVerComponent', () => {
  let component: ProductosVerComponent;
  let fixture: ComponentFixture<ProductosVerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductosVerComponent]
    });
    fixture = TestBed.createComponent(ProductosVerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
