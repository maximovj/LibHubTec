import { ToastrService } from 'ngx-toastr';
import { Component, inject, OnInit } from '@angular/core';
import { catchError, delay, map, Observable, of, startWith } from 'rxjs';

import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { CommonModule } from '@angular/common';

import { AuthService } from './../../../auth/services/auth-service.service';
import { SearchesService } from './../../services/searches.service';
import { SearchEntity } from '../../interfaces';
import { Router } from '@angular/router';
import { LoadingComponent } from '../../../shared/components/loading/loading.component';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    BadgeModule,
    ButtonModule,
    LoadingComponent,
  ],
  templateUrl: './user-search.component.html',
})

export class UserSearchComponent implements OnInit {

  private router = inject(Router);

  private searchesService = inject(SearchesService);

  private authService = inject(AuthService);

  private toastrService = inject(ToastrService);

  public searches$ !:Observable<any>;

  constructor() { }

  ngOnInit() {
    this.loadSearches();
  }

  private loadSearches() :void {
    const user = this.authService.user();
    this.searches$ = this.searchesService.listSearches(user)
      .pipe(
        delay(1000),
        map( (data: SearchEntity[] ) => {
          return { loading: false, data: data, err: null };
        }),
        startWith({ loading: true, data: null, err: null }),
        catchError( (err) => of({ loading: false, data: null, err }) )
      );
  }

  onSearch(item :SearchEntity) :void
  {
    const search_query = `${item.base_url}`;
    this.router.navigateByUrl(search_query);
  }

  onDelete(item :SearchEntity | null) :void
  {
    if(!item) {
      return;
    }

    this.searchesService.deleteSearches(item.id)
      .subscribe({
        next: () => {
          this.loadSearches();
          this.toastrService.success('Búsqueda eliminada');
        },
        error: () => {
          this.toastrService.error('Oops hubo un error en el proceso');
        }
      });
  }


}
