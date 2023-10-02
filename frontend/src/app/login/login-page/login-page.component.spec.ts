import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from 'src/app/app.component';

import { LoginPageComponent } from './login-page.component';

describe('LoginPageComponent', () => {
  let component: LoginPageComponent;
  let fixture: ComponentFixture<LoginPageComponent>;
  

    
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginPageComponent],
      imports: [HttpClientModule,BrowserAnimationsModule],
      providers: [AppComponent]

    }).compileComponents();

    fixture = TestBed.createComponent(LoginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
