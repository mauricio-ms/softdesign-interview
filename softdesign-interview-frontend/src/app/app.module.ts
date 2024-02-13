import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { BookListComponent } from '../book-list/book-list.component';
import { BookFormComponent } from '../book-form/book-form.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BookService } from '../service/book.service';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
    declarations: [
      AppComponent,
      BookListComponent,
      BookFormComponent
    ],
    imports: [
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      FormsModule
    ],
    providers: [BookService],
    bootstrap: [AppComponent]
  })
  export class AppModule { }