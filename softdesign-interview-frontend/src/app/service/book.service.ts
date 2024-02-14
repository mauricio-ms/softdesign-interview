import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../model/book';
import { Query } from '../model/query';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private booksApiUri: string;

  constructor(private http: HttpClient) {
    this.booksApiUri = 'http://localhost:8080/soft-design/books';
  }

  public getById(id: bigint): Observable<Book> {
    return this.http.get<Book>(`${this.booksApiUri}/${id}`);
  }

  public search(query: Query): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.booksApiUri}/search?q=${query.value}`);
  }

  public save(book: Book) {
    if (book.id) {
      return this.http.put<Book>(`${this.booksApiUri}/${book.id}`, book);
    }
    return this.http.post<Book>(this.booksApiUri, book);
  }

  public delete(id: bigint) {
    return this.http.delete<Book>(`${this.booksApiUri}/${id}`);
  }

  public rent(id: bigint) {
    return this.http.patch<Book>(`${this.booksApiUri}/${id}/rent`, null, { headers : {'Content-Type': 'application/json' }});
  }
}
