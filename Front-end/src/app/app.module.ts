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
import { CKEditorModule } from '../ckeditor/ckeditor.module';
import { RoleCreateComponent } from './role-create/role-create.component';
import { RoleManagementComponent } from './role-management/role-management.component';
import { AdminService } from './_service/admin_service/admin.service';
import { UserService } from './_service/user_service/user.service';
import { HeaderService } from './_service/header/header.service';
import { RoleEditComponent } from './role-edit/role-edit.component';
import { CatCreateComponent } from './cat-create/cat-create.component';
import { CatManagementComponent } from './cat-management/cat-management.component';
import { NewsCategoryComponent } from './news-category/news-category.component';
import { CatMenuComponent } from './cat-menu/cat-menu.component';
import { CatNewManagementComponent } from './cat-new-management/cat-new-management.component';

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
    UserManagementComponent,
    RoleCreateComponent,
    RoleManagementComponent,
    RoleEditComponent,
    CatCreateComponent,
    CatManagementComponent,
    NewsCategoryComponent,
    CatMenuComponent,
    CatNewManagementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CKEditorModule
  ],
  providers: [LoginService,
    NuServiceService,
  AdminService,
UserService,
HeaderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
