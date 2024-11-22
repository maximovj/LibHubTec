import { ReserveBookRequest } from './../interfaces/reserve-book-request.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { computed, inject, Injectable, signal } from "@angular/core";
import { catchError, delay, map, Observable, of, pipe, tap, throwError } from 'rxjs';
import { BooksResponse, ReserveBookResponse } from '../interfaces';
import { BookEntity } from '../interfaces';

@Injectable({ providedIn: "root" })
export class BooksService {

  private _books = signal<BookEntity[]>([]);
  public  books = computed(() =>  [...this._books()]);

  private _query = signal<boolean>(false);
  public query = computed(() => this._query());

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
    this._query.set(true);

    const _token = this.getToken();

    if(!_token) {
      this._query.set(false);
      return of(false);
    }

    return this.http.get<BooksResponse>(`http://localhost:5800/v1/books/${id}/book-details`, { headers: {
      'Authorization' : `Bearer ${_token}`,
    }})
    .pipe(
      delay(1000),
      map(({ response, data}) => {
        if(response.success && data){
          this._books.set(data);
        }
        return response?.success;
      }),
      catchError( (err) => throwError(() => err.message)),
      tap(() => {
        this._query.set(false);
      })
    );
  }

  // Registrar una reservación de libro
  public registerReserveBook(reserveBookRequest :ReserveBookRequest) : Observable<boolean>
  {
    this._query.set(true);
    const _token = this.getToken();

    if(!_token) {
      this._query.set(false);
      return of(false);
    }

    return this.http.post<ReserveBookResponse>('http://localhost:5800/v1/reserve/book/register', reserveBookRequest, {
      headers: {
        'Authorization' : `Bearer ${_token}`,
      }
    })
    .pipe(
      delay(1000),
      map(({response}) => {
        return response.success;
      }),
      catchError(err => throwError(() => err.message )),
      tap(() => {
        this._query.set(false);
      })
    );
  }

  // Obtener el token
  getToken() :string {
    const _token = localStorage.getItem('_token');
    return _token || '';
  }

}
