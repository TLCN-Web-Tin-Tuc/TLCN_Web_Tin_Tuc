import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsTypingComponent } from './news-typing.component';

describe('NewsTypingComponent', () => {
  let component: NewsTypingComponent;
  let fixture: ComponentFixture<NewsTypingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsTypingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsTypingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
