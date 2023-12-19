import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdenesVerComponent } from './ordenes-ver.component';

describe('OrdenesVerComponent', () => {
  let component: OrdenesVerComponent;
  let fixture: ComponentFixture<OrdenesVerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrdenesVerComponent]
    });
    fixture = TestBed.createComponent(OrdenesVerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
