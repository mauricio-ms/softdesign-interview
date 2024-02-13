import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../model/book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private booksApiUri: string;

  constructor(private http: HttpClient) {
    this.booksApiUri = 'http://localhost:8080/soft-design/books';
  }

  public search(query: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.booksApiUri}/search?q=${query}`);
  }

  public save(book: Book) {
    return this.http.post<Book>(this.booksApiUri, book);
  }

}
