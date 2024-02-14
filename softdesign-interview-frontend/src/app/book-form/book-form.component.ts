import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../model/book';
import { BookService } from '../service/book.service';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrl: './book-form.component.css'
})
export class BookFormComponent implements OnInit {

  book: Book;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService) {
    this.book = new Book();
  }

  ngOnInit() {
    const bookIdParam = this.route.snapshot.paramMap.get('id');
    if (bookIdParam) {
      this.bookService.getById(BigInt(bookIdParam)).subscribe(
        b => this.book = b
      );
    }
  }

  onSubmit() {
    this.bookService.save(this.book)
      .subscribe(result => this.gotoBookList());
  }

  gotoBookList() {
    this.router.navigate(['/books']);
  }
}
