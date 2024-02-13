import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookListComponent } from '../book-list/book-list.component';
import { BookFormComponent } from '../book-form/book-form.component';

export const routes: Routes = [
    { path : 'books', component: BookListComponent },
    { path : 'add-book', component: BookFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }