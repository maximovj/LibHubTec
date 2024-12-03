
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { catchError, delay, map, Observable, of, startWith } from 'rxjs';

import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ImageModule } from 'primeng/image';

import { AuthService } from '../../../auth/services/auth-service.service';
import { BooksService } from './../../../books/services/books-service.service';
import { LoadingComponent } from '../../../shared/components/loading/loading.component';
import { LoadingImageComponent } from '../../../shared/components/loading-image/loading-image.component';
import { ReserveBookData } from '../../../books/interfaces';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    ImageModule,
    CardModule,
    ButtonModule,
    LoadingComponent,
    LoadingImageComponent,
  ],
  templateUrl: "./user-books.component.html",
})
export class UserBooksComponent implements OnInit {

  private router = inject(Router);

  private authService = inject(AuthService);

  public booksService = inject(BooksService);

  public listReserveBook$ !:Observable<any>;

  constructor() { }

  ngOnInit() {
    const user = this.authService.user();
    this.listReserveBook$ = this.booksService
    .getListReservedBooksByAccount(user)
    .pipe(
      delay(1000),
      map( (data :ReserveBookData[] )  => {
        return { loading: false, data, err: null };
      }),
      startWith({ loading: true, data: [], err: null }),
      catchError(err => of({ loading: false, data: [], err }))
    );
  }

  public bntGoTo(id :number) :void
  {
    this.router.navigateByUrl(`/books/p/${id}`);
  }

}
