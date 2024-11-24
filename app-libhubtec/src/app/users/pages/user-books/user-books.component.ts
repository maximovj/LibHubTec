import { catchError, delay, firstValueFrom, map, Observable, of, startWith } from 'rxjs';
import { AuthService } from '../../../auth/services/auth-service.service';
import { BooksService } from './../../../books/services/books-service.service';
import { Component, inject, OnInit } from '@angular/core';
import { ReserveBookData } from '../../../books/interfaces';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  imports: [
    CommonModule,
  ],
  templateUrl: "./user-books.component.html",
})
export class UserBooksComponent implements OnInit {

  private authService = inject(AuthService);

  public booksService = inject(BooksService);

  public listReserveBook$ ?:Observable<any>;

  constructor() { }

  ngOnInit() {
    this.listReserveBook$ = this.booksService
    .getListReservedBooksByAccount(this.authService.user())
    .pipe(
      delay(1000),
      map( data => ({ loading: false, data, err: null })),
      startWith({ loading: true, data: [], err: null }),
      catchError(err => of({ loading: false, data: [], err }))
    );
  }

}
