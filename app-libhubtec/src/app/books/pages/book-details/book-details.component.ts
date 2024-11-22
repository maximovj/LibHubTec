import { AuthService } from './../../../auth/services/auth-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Component, computed, inject, OnInit } from '@angular/core';

import { ToastrService } from 'ngx-toastr';

import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ImageModule } from 'primeng/image';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

import { BookEntity, ReserveBookRequest } from '../../interfaces';
import { BooksService } from './../../services/books-service.service';
import { ThumbnailPipe } from '../../pipes/thumbnail.pipe';


@Component({
  standalone: true,
  templateUrl: './book-details.component.html',
  styles: ``,
  imports: [
    CommonModule,
    ImageModule,
    CardModule,
    ButtonModule,
    ThumbnailPipe,
    ProgressSpinnerModule,
  ],
})

export class BookDetailsComponent implements OnInit {

  private authService = inject(AuthService);

  private toastrService = inject(ToastrService);

  private booksService = inject(BooksService);

  private router = inject(Router);

  private activedRoute = inject(ActivatedRoute);

  public fetch_query = computed(() => this.booksService.query());

  public book!: BookEntity;

  public reserved :boolean = false;

  constructor() { }

  ngOnInit() {
    this.activedRoute.params.subscribe((params) => {
      const id_book = params['id'];
      this.booksService.getBookById(id_book).subscribe({
        next: () => {
          this.book = {...this.booksService.books()[0], thumbnail: undefined};
          this.toastrService.success('Información del libro capturado');
        },
        error: () => {
          this.router.navigateByUrl('/books/list');
          this.toastrService.error('Error en la captura de la información del libro');
        },
      });
    });
  }

  onReserveBook() :void
  {
    const user_id =  this.authService.user()?.id;

    const reserveBookRequest : ReserveBookRequest = {
      account_id: user_id,
      book_id: this.book.id,
      date_from: '2024-01-11',
      date_to: '2024-11-11',
    };

    if(this.reserved){
      this.reserved = !this.reserved;
      this.toastrService.info('Liberando libro de la biblioteca');
      return;
    }

    this.booksService.registerReserveBook(reserveBookRequest)
    .subscribe({
      next: () => {
        this.reserved = true;
        this.toastrService.success('Libro reservado exitosamente.');
      },
      error: () => {
        this.reserved = false;
        this.toastrService.error('Oops hubo un error la reservación.');
      },
    });
  }

}
