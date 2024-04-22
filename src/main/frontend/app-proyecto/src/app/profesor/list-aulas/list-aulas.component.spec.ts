import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAulasComponent } from './list-aulas.component';

describe('ListAulasComponent', () => {
  let component: ListAulasComponent;
  let fixture: ComponentFixture<ListAulasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListAulasComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListAulasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
