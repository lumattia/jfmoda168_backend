import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AulaUsuariosComponent } from './usuarios-profesor.component';

describe('AulaUsuariosComponent', () => {
  let component: AulaUsuariosComponent;
  let fixture: ComponentFixture<AulaUsuariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AulaUsuariosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AulaUsuariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
