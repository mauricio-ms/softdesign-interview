import { Component, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { Query } from '../model/query';
import { BookService } from '../service/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.css'
})
export class BookListComponent implements OnInit {

  query: Query;
  books: Book[];

  constructor(private bookService: BookService) {
    this.query = new Query();
    this.books = [];
  }

  ngOnInit() {
    this.search(); 
  }

  search() {
    this.bookService.search(this.query).subscribe((data: Book[]) => {
      this.books = data;
    });
  }
}
