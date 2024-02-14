import { Component, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { Query } from '../model/query';
import { BookService } from '../service/book.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.css'
})
export class BookListComponent implements OnInit {

  query: Query;
  books: Book[];

  constructor(
    private bookService: BookService,
    private router: Router) {
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

  editBook(bookId: bigint) {
    this.router.navigate(['/book', bookId]);
  }

  removeBook(bookId: bigint) {
    this.bookService.delete(bookId).subscribe(() => {
      this.search();
    });
  }
}
