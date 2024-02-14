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

  editBook(book: Book) {
    if (book.rented) {
      alert(`Book ${book.id} cannot be updated because it's rent.`);
    } else {
      this.router.navigate(['/book', book.id]);
    }
  }

  removeBook(bookId: bigint) {
    this.bookService.delete(bookId).subscribe({
      next: () => this.search(),
      error: (e) => alert(e.error.detail)
    });
  }

  rentBook(bookId: bigint) {
    this.bookService.rent(bookId).subscribe({
      next: () => this.search(),
      error: (e) => alert(e.error.detail)
    });
  }
}
