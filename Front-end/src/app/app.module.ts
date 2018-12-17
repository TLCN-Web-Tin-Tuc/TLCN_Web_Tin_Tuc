import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { FramesComponent } from './frames/frames.component';
import { MenuComponent } from './menu/menu.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { ProfileComponent } from './profile/profile.component';
import { ProfileEditComponent } from './profile-edit/profile-edit.component';
import { PasswordEditComponent } from './password-edit/password-edit.component';
import { NewsManagementComponent } from './news-management/news-management.component';
import { NewsTypingComponent } from './news-typing/news-typing.component';
import { NewsDetailComponent } from './news-detail/news-detail.component';
import { NewsIndexComponent } from './news-index/news-index.component';
import { LoginService } from './_service/login.service';
import { NuServiceService } from './_service/nu_service/nu-service.service';
import { UserManagementComponent } from './user-management/user-management.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    FramesComponent,
    MenuComponent,
    LoginComponent,
    RegisterComponent,
    ResetpasswordComponent,
    ProfileComponent,
    ProfileEditComponent,
    PasswordEditComponent,
    NewsManagementComponent,
    NewsTypingComponent,
    NewsDetailComponent,
    NewsIndexComponent,
    UserManagementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [LoginService,
    NuServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
