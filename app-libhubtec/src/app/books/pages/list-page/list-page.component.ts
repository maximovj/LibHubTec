import { ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';
import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ImageModule } from 'primeng/image';

import { ToastrService } from 'ngx-toastr';

import { BooksService } from './../../services/books-service.service';
import { LoadingImageComponent } from '../../../shared/components/loading-image/loading-image.component';

@Component({
  standalone: true,
  imports: [
    ImageModule,
    CardModule,
    ButtonModule,
    LoadingImageComponent,
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

  private activatedRoute = inject(ActivatedRoute);
  private toastrService = inject(ToastrService);

  private booksService = inject(BooksService);

  private router = inject(Router);

  public books = computed(() => [...this.booksService.books()]);

  constructor() { }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe((queryParam)=> {
      const search_query = queryParam.get('q');

      this.booksService
      .searchBooks(search_query)
      .subscribe({
        next: () => {
          this.toastrService.success('Lista de libros cargados exitosamente.', 'Libros');
        },
        error: (err) => console.log(err),
      });
    });


  }

  bntGoTo(id :number) :void
  {
    this.router.navigateByUrl(`/books/p/${id}`);
  }

}
