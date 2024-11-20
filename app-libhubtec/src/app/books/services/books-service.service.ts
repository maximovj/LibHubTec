import { HttpClient, HttpHeaders } from '@angular/common/http';
import { computed, inject, Injectable, signal } from "@angular/core";
import { catchError, map, Observable, of, tap, throwError } from 'rxjs';
import { BooksResponse } from '../interfaces';
import { BookEntity } from '../interfaces';

@Injectable({ providedIn: "root" })
export class BooksService {

  private _books = signal<BookEntity[]>([]);
  public  books = computed(() =>  [...this._books()]);

  private http = inject(HttpClient);

  constructor() { }

  loadBooks() : Observable<boolean>
  {
    const _token = this.getToken();

    if(!_token) { return of(false); }

    const headers = new HttpHeaders({
      'Authorization' : `Bearer ${_token}`,
    });

    return this.http.get<BooksResponse>('http://localhost:5800/v1/books', { headers })
    .pipe(
      map(({data, response})=> {
        if(response?.success){
          console.log(data, response);
          this._books.set(data);
        }
        return response?.success;
      }),
      catchError(err => throwError(() => ({ message: err.message, class: 'BooksService::loadBooks' }))),
    );
  }

  getToken() :string {
    const _token = localStorage.getItem('_token');
    return _token || '';
  }

}
