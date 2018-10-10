import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
import {RouterModule, Routes } from '@angular/router';
import { FramesComponent } from './frames/frames.component';

const routes: Routes = [
  { path: '', component: FramesComponent }
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
