import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatNewManagementComponent } from './cat-new-management.component';

describe('CatNewManagementComponent', () => {
  let component: CatNewManagementComponent;
  let fixture: ComponentFixture<CatNewManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatNewManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatNewManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
