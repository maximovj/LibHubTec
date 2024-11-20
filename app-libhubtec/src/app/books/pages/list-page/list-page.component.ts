import { BooksService } from './../../services/books-service.service';
import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { ImageModule } from 'primeng/image';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';

import { ToastrService } from 'ngx-toastr';
import { ThumbnailPipe } from '../../pipes/thumbnail.pipe';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [
    ImageModule,
    CardModule,
    ButtonModule,
    ThumbnailPipe,
  ],
  styles: `
    .book-thumbnail {
      width: 100%;
      height: 100px;
      object-fit: cover;
    }
  `,
  selector: 'books-list-page',
  templateUrl: './list-page.component.html',
})

export class BooksListComponent implements OnInit {

  private toastrService = inject(ToastrService);
  private booksService = inject(BooksService);
  private router = inject(Router);

  public books = computed(() => [...this.booksService.books()]);

  constructor() { }

  ngOnInit() {
    this.booksService
    .loadBooks()
    .subscribe({
      next: (succes) => {
        this.toastrService.success('Lista de libros cargados exitosamente.', 'Libros');
      },
      error: (err) => console.log(err),
    });
  }

  bntGoTo(id :number) :void
  {
    this.router.navigateByUrl(`/books/p/${id}`);
  }

}
