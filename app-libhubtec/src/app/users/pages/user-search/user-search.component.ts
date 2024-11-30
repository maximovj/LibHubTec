import { Component, inject, OnInit } from '@angular/core';
import { catchError, delay, map, Observable, of, startWith } from 'rxjs';

import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { CommonModule } from '@angular/common';

import { AuthService } from './../../../auth/services/auth-service.service';
import { SearchesService } from './../../services/searches.service';
import { SearchEntity } from '../../interfaces';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    BadgeModule,
    ButtonModule,

  ],
  templateUrl: './user-search.component.html',
})

export class UserSearchComponent implements OnInit {

  private searchesService = inject(SearchesService);
  private authService = inject(AuthService);

  public searches$ ?:Observable<any>;

  constructor() { }

  ngOnInit() {
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

}
