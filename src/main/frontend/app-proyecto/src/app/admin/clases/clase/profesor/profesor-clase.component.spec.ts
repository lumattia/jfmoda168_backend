import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfesorClaseComponent } from './profesor-clase.component';

describe('ProfesorComponent', () => {
  let component: ProfesorClaseComponent;
  let fixture: ComponentFixture<ProfesorClaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfesorClaseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfesorClaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
