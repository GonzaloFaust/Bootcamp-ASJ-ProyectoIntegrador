import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductosCrearComponent } from './productos-crear.component';

describe('ProductosCrearComponent', () => {
  let component: ProductosCrearComponent;
  let fixture: ComponentFixture<ProductosCrearComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductosCrearComponent]
    });
    fixture = TestBed.createComponent(ProductosCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
