import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatMenuComponent } from './cat-menu.component';

describe('CatMenuComponent', () => {
  let component: CatMenuComponent;
  let fixture: ComponentFixture<CatMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
