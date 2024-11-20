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

  // Cargar todo los libros
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

  // Cargar un libro por id
  public getBookById(id :number) :Observable<boolean>
  {
    const _token = this.getToken();

    if(!_token) return of(false);

    this._books.set([
      {
        id: 1,
        thumbnail: "WLn9uTWsNZH8inuUYuOAFlxcKFt2CDPkrp5Z6VBy.jpg",
        title: "Ciberactivismo",
        author: "Mario Táscon y Yolanda Quintana",
        summary: "La historia del internet, como hacer activismo desde la red.",
        description: "Es un libro genial, tipo manual para las personas activistas.",
        updatedAt: new Date("2024-11-15T23:10:19"),
        createdAt: new Date("2024-11-15T23:10:19"),
    },
    ]);

    return this.http.get<BooksResponse>(`http://localhost:5800/v1/books/${id}/book-details`, { headers: {
      'Authorization' : `Bearer ${_token}`,
    }})
    .pipe(
      map(({ response, data}) => {
        console.log({ response, data });
        //if(response.success && data){
        //  this._books.set(data);
        //}
        return response?.success;
      }),
      catchError( (err) => throwError(() => err.message)),
    );
  }

  // Obtener el token
  getToken() :string {
    const _token = localStorage.getItem('_token');
    return _token || '';
  }

}
