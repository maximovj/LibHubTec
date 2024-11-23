import { AuthService } from './../../auth/services/auth-service.service';
import { BooksService } from './../services/books-service.service';
import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { catchError, firstValueFrom, forkJoin, Observable, of, switchMap, throwError } from "rxjs";
import { BookDetailsEntity, BookEntity, ReserveBookData } from '../interfaces';

@Injectable({ providedIn: 'root' })
export class BookDetailsResolve  implements Resolve<BookDetailsEntity> {
  constructor(private booksService: BooksService, private authService: AuthService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<BookDetailsEntity> {
    const book_id = route.paramMap.get('id');

    return this.booksService.getBookDetailsById(parseInt(book_id || '-1'))
    .pipe(
      switchMap( (value) => of(value)),
      switchMap( async (book: BookEntity) => {
        const bookDetailsEntity : BookDetailsEntity = {
          reserve_book_id: null,
          account_id: null,
          book_id: book.id,
          date_from: null,
          date_to: null,
          isReserved: false,
          book,
        };

        const user_id = this.authService.user()!.id;
        const reserveBook$ = this.booksService.findReserveBookByBookAndAccount(book.id, user_id || -1);
        const reserve_book : ReserveBookData | null = await firstValueFrom(reserveBook$);

        if(reserve_book && book){
          bookDetailsEntity.reserve_book_id = reserve_book.id;
          bookDetailsEntity.account_id = reserve_book.account.id;
          bookDetailsEntity.book_id = reserve_book.book.id;
          bookDetailsEntity.date_from = reserve_book.date_from;
          bookDetailsEntity.date_to = reserve_book.date_to;
          bookDetailsEntity.isReserved = true;
          return bookDetailsEntity;
        }

        return bookDetailsEntity;
      }),
    );
  }

}
