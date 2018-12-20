import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatManagementComponent } from './cat-management.component';

describe('CatManagementComponent', () => {
  let component: CatManagementComponent;
  let fixture: ComponentFixture<CatManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
