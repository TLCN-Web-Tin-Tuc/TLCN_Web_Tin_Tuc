import { NgModule } from '@angular/core';
import {RouterModule, Routes } from '@angular/router';
import { FramesComponent } from './frames/frames.component';
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
import { UserManagementComponent } from './user-management/user-management.component';
import { RoleCreateComponent } from './role-create/role-create.component';
import { RoleManagementComponent } from './role-management/role-management.component';
import { RoleEditComponent } from './role-edit/role-edit.component';
import { CatCreateComponent } from './cat-create/cat-create.component';
import { CatManagementComponent } from './cat-management/cat-management.component';
import { NewsCategoryComponent } from './news-category/news-category.component';
import { CatNewManagementComponent } from './cat-new-management/cat-new-management.component';

const routes: Routes = [
  { path: 'test', component: FramesComponent },
  { path: '', component: NewsIndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'recoverypassword', component: ResetpasswordComponent },
  { path: 'profile/:id', component: ProfileComponent },
  { path: 'editprofile', component: ProfileEditComponent },
  { path: 'editpassword', component: PasswordEditComponent },
  { path: 'newsmanagement', component: NewsManagementComponent },
  { path: 'newstyping', component: NewsTypingComponent },
  { path: 'newsdetail/:id', component: NewsDetailComponent },
  { path: 'newscategory/:id', component: NewsCategoryComponent },  
  { path: 'usersmanagement', component: UserManagementComponent },
  { path: 'rolecreate', component: RoleCreateComponent },
  { path: 'rolesmanagement', component: RoleManagementComponent },
  { path: 'catcreate', component: CatCreateComponent },
  { path: 'catmanagement', component: CatManagementComponent },
  { path: 'editrole/:id', component: RoleEditComponent },
  { path: 'catnewmanagement', component: CatNewManagementComponent }
];

@NgModule({
  imports: [
    // CommonModule
    RouterModule.forRoot(routes)
  ],
//  declarations: []
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
